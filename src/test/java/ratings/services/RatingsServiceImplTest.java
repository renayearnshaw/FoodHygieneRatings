package ratings.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratings.exceptions.NotFoundException;
import ratings.model.Establishment;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RatingsServiceImplTest {

    private static long NON_EXISTENT_AUTHORITY = 999999L;

    @Mock
    private EstablishmentsService establishmentsService;

    private RatingsService ratingsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ratingsService = new RatingsServiceImpl(establishmentsService);
    }

    @Test(expected = NotFoundException.class)
    public void testAuthorityDoesNotExist() {
        //Given
        when(establishmentsService.getEstablishmentsInAuthority(NON_EXISTENT_AUTHORITY))
                .thenReturn(Collections.<Establishment>emptyList());

        //When
        ratingsService.getRatingSummaryForAuthority(NON_EXISTENT_AUTHORITY);

        //Then
        // Should throw exception

    }
}