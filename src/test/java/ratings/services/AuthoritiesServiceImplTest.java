package ratings.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ratings.model.Authority;
import ratings.model.AuthorityResponse;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthoritiesServiceImplTest {

    private static final String VERSION_KEY = "x-api-version";
    private static final String VERSION_VALUE = "2";
    private static final String AUTHORITIES_URI = "http://api.ratings.food.gov.uk/Authorities/basic";

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestTemplate restTemplate;

    private AuthoritiesService authoritiesService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authoritiesService = new AuthoritiesServiceImpl(VERSION_KEY, VERSION_VALUE, 1, 20, AUTHORITIES_URI, restTemplate);
    }

    @Test
    public void testGetAuthorities() {

        //Given
        AuthorityResponse authorityResponse = new AuthorityResponse();
        authorityResponse.setAuthorities(Collections.singletonList(new Authority()));
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AuthorityResponse.class))
                .getBody()).thenReturn(authorityResponse);

        //When
        List<Authority> authorities = authoritiesService.getAuthorities();

        //Then
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AuthorityResponse.class));
        assertNotNull(authorities);
        assertEquals(1, authorities.size());

    }

    @Test(expected = HttpClientErrorException.class)
    public void testGetAuthoritiesWithWrongVersionOrBadURI() {
        //Given
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(AuthorityResponse.class))
                .getBody()).thenThrow(HttpClientErrorException.class);

        //When
        List<Authority> authorities = authoritiesService.getAuthorities();
    }

}