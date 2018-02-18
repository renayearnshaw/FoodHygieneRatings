package ratings.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import ratings.model.Authority;
import ratings.service.AuthoritiesService;
import ratings.service.AuthoritiesServiceImpl;

public class AuthoritiesServiceIT {
	private static final String VERSION_KEY = "x-api-version";
	private static final String VERSION_VALUE = "2";
	private static final String WRONG_VERSION_VALUE = "1";
	private static final String AUTHORITIES_URI = "http://api.ratings.food.gov.uk/Authorities/basic";
	private static final String WRONG_AUTHORITIES_URI = "http://api.ratings.food.gov.uk/AuthoritiesXXX/basic";
	private static AuthoritiesService authoritiesService;
	private List<Authority> authorities;
	
	@Test
	public void testAuthoritiesNotNull() {
		authoritiesService = new AuthoritiesServiceImpl(VERSION_KEY, VERSION_VALUE, AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
		assertNotNull(authorities);
	}
	
	@Test
	public void testAuthoritiesNotZero() {
		authoritiesService = new AuthoritiesServiceImpl(VERSION_KEY, VERSION_VALUE, AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
		assertNotEquals(0, authorities.size());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testAuthoritiesWithWrongVersionThrowsException() {
		authoritiesService = new AuthoritiesServiceImpl(VERSION_KEY, WRONG_VERSION_VALUE, AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testAuthoritiesWithWrongUriThrowsException() {
		authoritiesService = new AuthoritiesServiceImpl(VERSION_KEY, VERSION_VALUE, WRONG_AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
	}
}
