package ratings.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class AuthorityTest {

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
    public void testDeSerializingWithIgnoredField() throws IOException {
        String jsonString = "{\"LocalAuthorityId\": 197,\n" +
                "            \"LocalAuthorityIdCode\": \"760\",\n" +
                "            \"Name\": \"Aberdeen City\"}";
        Authority authority = objectMapper.readValue(jsonString, Authority.class);
        assertThat(authority.getId(), Matchers.is(equalTo(197L)));
        assertThat(authority.getName(), Matchers.is(equalTo("Aberdeen City")));
    }
}