package ratings.services;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratings.exceptions.NotFoundException;
import ratings.model.Establishment;
import ratings.model.Rating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RatingsServiceImplTest {

    private static final long NON_EXISTENT_AUTHORITY_ID = 999999L;
    private static final long VALID_AUTHORITY_ID = 100L;

    @Mock
    private EstablishmentsService establishmentsService;

    private RatingsService ratingsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ratingsService = new RatingsServiceImpl(establishmentsService);
    }

    @Test
    public void testGetRatingsForAuthority() {
        //Given
        List<Establishment> estabishments = Arrays.asList(
                new Establishment("Yummy Garden 1", Rating.FIVE_STAR),
                new Establishment("Yummy Garden 2", Rating.FIVE_STAR),
                new Establishment("Yummy Garden 3", Rating.FIVE_STAR),
                new Establishment("Yummy Garden 4", Rating.ONE_STAR)
        );
        when(establishmentsService.getEstablishmentsInAuthority(VALID_AUTHORITY_ID))
                .thenReturn(estabishments);

        //When
        Map<String, String> ratingsSummary = ratingsService.getRatingSummaryForAuthority(VALID_AUTHORITY_ID);

        //Then
        assertNotNull(ratingsSummary);
        assertThat(ratingsSummary, IsMapContaining.hasEntry(Rating.FIVE_STAR.toString(), "75.00%"));
        assertThat(ratingsSummary, IsMapContaining.hasEntry(Rating.ONE_STAR.toString(), "25.00%"));

    }

    @Test(expected = NotFoundException.class)
    public void testAuthorityDoesNotExist() {
        //Given
        when(establishmentsService.getEstablishmentsInAuthority(NON_EXISTENT_AUTHORITY_ID))
                .thenReturn(Collections.<Establishment>emptyList());

        //When
        ratingsService.getRatingSummaryForAuthority(NON_EXISTENT_AUTHORITY_ID);

        //Then
        // Should throw exception

    }
}