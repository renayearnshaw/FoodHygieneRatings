package ratings.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ratings.model.Authority;
import ratings.service.AuthoritiesService;
import ratings.service.RatingsService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FoodHygieneRatingsControllerTest {

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private RatingsService ratingsService;

    private FoodHygieneRatingsController ratingsController;
    private static final long AUTHORITY_ID = 275;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ratingsController = new FoodHygieneRatingsController(authoritiesService, ratingsService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ratingsController).build();

        mockMvc.perform(get("/foodhygiene/authorities"))
                .andExpect(status().isOk());
        mockMvc.perform(get(String.format("/foodhygiene/authorities/%d/ratings/summary", AUTHORITY_ID)))
                .andExpect(status().isOk());
    }

    @Test
    public void getAuthorities() {
        //Given
        List<Authority> authorities = Collections.singletonList(new Authority());
        when(authoritiesService.getAuthorities()).thenReturn(authorities);

        //When
        List<Authority> returnedAuthorities = ratingsController.getAuthorities();

        //Then
        verify(authoritiesService, times(1)).getAuthorities();
        assertEquals(authorities, returnedAuthorities);
    }

    @Test
    public void getRatingsSummary() {
        //Given
        final Map<String, String> ratings = Collections.singletonMap("1-star", "2.57%");

        when(ratingsService.getRatingSummaryForAuthority(AUTHORITY_ID)).thenReturn(ratings);

        //When
        Map<String, String> returnValue = ratingsController.getRatingsSummary(AUTHORITY_ID);

        //Then
        assertEquals(ratings, returnValue);
        verify(ratingsService, times(1)).getRatingSummaryForAuthority(eq(AUTHORITY_ID));
    }
}