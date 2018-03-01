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
import ratings.services.RatingsService;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RatingsControllerTest {

    private static final String VIEW_NAME = "ratings";
    private static final long AUTHORITY_ID = 275;
    private static final String RATING_DESCRIPTION = "1-star";
    private static final String RATING_PERCENTAGE = "2.57%";

    @Mock
    private RatingsService ratingsService;

    @Mock
    private
    Model model;

    private RatingsController ratingsController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ratingsController = new RatingsController(ratingsService);
    }

    @Test
    public void testMockMVC() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ratingsController).setViewResolvers(viewResolver).build();

        mockMvc.perform(get(String.format("/foodhygiene/authorities/%d/ratings", AUTHORITY_ID)))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void getRatings() {
        //Given
        final Map<String, String> ratings = Collections.singletonMap(RATING_DESCRIPTION, RATING_PERCENTAGE);
        when(ratingsService.getRatingSummaryForAuthority(AUTHORITY_ID)).thenReturn(ratings);
        @SuppressWarnings("unchecked")
        ArgumentCaptor<Map<String, String>> argumentCaptor = ArgumentCaptor.forClass(Map.class);

        //When
        String viewName = ratingsController.getRatingsSummary(AUTHORITY_ID, model);

        //Then
        assertEquals(viewName, VIEW_NAME);
        verify(ratingsService, times(1)).getRatingSummaryForAuthority(eq(AUTHORITY_ID));
        verify(model, times(1)).addAttribute(eq("ratings"), argumentCaptor.capture());
        Map<String, String> ratingsInController = argumentCaptor.getValue();
        assertEquals(1, ratingsInController.size());
        assertEquals(RATING_PERCENTAGE, ratingsInController.get(RATING_DESCRIPTION));
        verify(model, times(1)).addAttribute(eq("authorityId"), eq(AUTHORITY_ID));
    }
}