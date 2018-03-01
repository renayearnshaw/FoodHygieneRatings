package ratings.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ratings.model.Authority;
import ratings.services.AuthoritiesService;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AuthoritiesControllerTest {

    private static final String VIEW_NAME = "authorities";

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private Model model;

    private AuthoritiesController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new AuthoritiesController(authoritiesService);
    }

    @Test
    public void testMockMVC() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();

        mockMvc.perform(get("/foodhygiene/authorities"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void getAuthorities() {
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