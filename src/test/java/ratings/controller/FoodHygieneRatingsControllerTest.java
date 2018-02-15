package ratings.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

public class FoodHygieneRatingsControllerTest {

    @Mock
    private AuthoritiesService authoritiesService;

    @Mock
    private RatingsService ratingsService;

    private FoodHygieneRatingsController ratingsController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ratingsController = new FoodHygieneRatingsController(authoritiesService, ratingsService);
    }

    @Test
    public void getAuthorities() {
        List<Authority> authorities = Collections.singletonList(new Authority());
        when(authoritiesService.getAuthorities()).thenReturn(authorities);

        List<Authority> returnValue = ratingsController.getAuthorities();

        verify(authoritiesService, times(1)).getAuthorities();
        assertEquals(authorities, returnValue);
    }

    @Test
    public void getRatingsSummary() {
        final long AUTHORITY_ID = 275;
        final Map<String, String> ratings = Collections.singletonMap("1-star", "2.57%");

        when(ratingsService.getRatingSummaryForAuthority(AUTHORITY_ID)).thenReturn(ratings);

        Map<String, String> returnValue = ratingsController.getRatingsSummary(AUTHORITY_ID);

        assertEquals(ratings, returnValue);
        verify(ratingsService, times(1)).getRatingSummaryForAuthority(eq(AUTHORITY_ID));
    }
}