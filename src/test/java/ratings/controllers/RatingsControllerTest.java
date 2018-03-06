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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ratings.exceptions.NotFoundException;
import ratings.services.RatingsService;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ratings.controllers.RatingsController.VIEW_NAME;
import static ratings.controllers.utils.ControllerUtils.ERROR_VIEW_NAME;

public class RatingsControllerTest {

    private static final long AUTHORITY_ID = 275;
    private static final long NON_EXISTENT_AUTHORITY_ID = 999999;
    private static final String NON_NUMERIC_AUTHORITY_ID = "klsjdflkds";
    private static final String RATING_DESCRIPTION = "1-star";
    private static final String RATING_PERCENTAGE = "2.57%";

    @Mock
    private RatingsService ratingsService;

    @Mock
    private
    Model model;

    private RatingsController ratingsController;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ratingsController = new RatingsController(ratingsService);
        mockMvc = getMockMvcWithViewResolver();
    }

    private MockMvc getMockMvcWithViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        return MockMvcBuilders.standaloneSetup(ratingsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testGetRatings() throws Exception {
        mockMvc.perform(get(String.format("/foodhygiene/authorities/%d/ratings", AUTHORITY_ID)))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME));
    }

    @Test
    public void testAuthorityNotFound() throws Exception {
        //Given
        when(ratingsService.getRatingSummaryForAuthority(anyLong())).thenThrow(NotFoundException.class);

        //When,Then
        mockMvc.perform(get(String.format("/foodhygiene/authorities/%d/ratings", NON_EXISTENT_AUTHORITY_ID)))
                .andExpect(status().isNotFound())
                .andExpect(model().attribute("httpStatus", HttpStatus.NOT_FOUND))
                .andExpect(model().attribute("exception",  instanceOf(NotFoundException.class)))
                .andExpect(view().name(ERROR_VIEW_NAME));
    }

    @Test
    public void testNonNumericAuthority() throws Exception {
        //When,Then
        mockMvc.perform(get(String.format("/foodhygiene/authorities/%s/ratings", NON_NUMERIC_AUTHORITY_ID)))
                .andExpect(status().isBadRequest())
                .andExpect(model().attribute("httpStatus", HttpStatus.BAD_REQUEST))
                .andExpect(model().attribute("exception",  instanceOf(MethodArgumentTypeMismatchException.class)))
                .andExpect(view().name(ERROR_VIEW_NAME));
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