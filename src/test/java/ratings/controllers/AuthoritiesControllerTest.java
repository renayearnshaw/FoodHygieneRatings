package ratings.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ratings.model.Authority;
import ratings.services.AuthoritiesService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AuthoritiesControllerTest {

    private static final String VIEW_NAME = "authorities";
    private static final String ERROR_VIEW_NAME = "errorView";

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private Model model;

    private AuthoritiesController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AuthoritiesController(authoritiesService);
        mockMvc = getMockMvcWithViewResolver();
    }

    private MockMvc getMockMvcWithViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        return MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }


    @Test
    public void testGetAuthorities() throws Exception {
        mockMvc.perform(get("/foodhygiene/authorities"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void testInvalidVersionOrURI() throws Exception {
        //Given
        when(authoritiesService.getAuthorities()).thenThrow(HttpClientErrorException.class);

        //When,Then
        mockMvc.perform(get(String.format("/foodhygiene/authorities")))
                .andExpect(status().isBadRequest())
                .andExpect(model().attribute("httpStatus", HttpStatus.BAD_REQUEST))
                .andExpect(model().attribute("exception",  instanceOf(HttpClientErrorException.class)))
                .andExpect(view().name(ERROR_VIEW_NAME));
    }

    @Test
    public void getAuthoritiesMethod() {
        //Given
        List<Authority> authorities = Collections.singletonList(new Authority());
        when(authoritiesService.getAuthorities()).thenReturn(authorities);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Authority>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //When
        String viewName = controller.getAuthorities(model);

        //Then
        assertEquals(viewName, VIEW_NAME);
        verify(authoritiesService, times(1)).getAuthorities();
        verify(model, times(1)).addAttribute(eq("authorities"), argumentCaptor.capture());
        List<Authority> authoritiesInController = argumentCaptor.getValue();
        assertEquals(1, authoritiesInController.size());
    }
}