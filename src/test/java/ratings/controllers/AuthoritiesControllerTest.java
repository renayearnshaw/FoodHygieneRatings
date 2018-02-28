package ratings.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ratings.model.Authority;
import ratings.services.AuthoritiesService;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthoritiesControllerTest {

    @Mock
    private AuthoritiesService authoritiesService;

    private AuthoritiesController authoritiesController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authoritiesController = new AuthoritiesController(authoritiesService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authoritiesController).build();

        mockMvc.perform(get("/foodhygiene/authorities"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAuthorities() {
        //Given
        List<Authority> authorities = Collections.singletonList(new Authority());
        when(authoritiesService.getAuthorities()).thenReturn(authorities);

        //When
        List<Authority> returnedAuthorities = authoritiesController.getAuthorities();

        //Then
        verify(authoritiesService, times(1)).getAuthorities();
        assertEquals(authorities, returnedAuthorities);
    }
}