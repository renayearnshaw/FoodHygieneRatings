package ratings.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EstablishmentTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
    }

    @Test
    public void testDeSerializingWithCorrectRatingValue() throws IOException {
        String jsonString = "{\"BusinessName\": \"1 & 30 DONALD DEWAR COURT\",\n" +
                "\"RatingValue\": \"Pass\"}";
        Establishment establishment = objectMapper.readValue(jsonString, Establishment.class);
        assertThat(establishment.getName(), is(equalTo("1 & 30 DONALD DEWAR COURT")));
        assertThat(establishment.getRating(), is(equalTo(Rating.PASS)));

    }

    @Test
    public void testDeSerializingWithRatingValueMissingSpaces() throws IOException {
        String jsonString = "{\"BusinessName\": \"1 & 30 DONALD DEWAR COURT\",\n" +
                "\"RatingValue\": \"AwaitingInspection\"}";
        Establishment establishment = objectMapper.readValue(jsonString, Establishment.class);
        assertThat(establishment.getName(), is(equalTo("1 & 30 DONALD DEWAR COURT")));
        assertThat(establishment.getRating(), is(equalTo(Rating.AWAITING_INSPECTION)));

    }

    @Test
    public void testDeSerializingWithUnknownRatingValue() throws IOException {
        String jsonString = "{\"BusinessName\": \"1 & 30 DONALD DEWAR COURT\",\n" +
                "\"RatingValue\": \"Unknown Value\"}";
        Establishment establishment = objectMapper.readValue(jsonString, Establishment.class);
        assertThat(establishment.getName(), is(equalTo("1 & 30 DONALD DEWAR COURT")));
        assertThat(establishment.getRating(), is(equalTo(Rating.UNKNOWN)));

    }
}