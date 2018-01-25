package ratings.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import ratings.model.Authority;
import ratings.service.AuthoritiesService;

public class AuthoritiesServiceTest {
	private static final String VERSION = "2";
	private static final String WRONG_VERSION = "1";
	private static final String AUTHORITIES_URI = "http://api.ratings.food.gov.uk/Authorities/basic";
	private static final String WRONG_AUTHORITIES_URI = "http://api.ratings.food.gov.uk/AuthoritiesXXX/basic";
	private static AuthoritiesService authoritiesService;
	private List<Authority> authorities;
	
	@Test
	public void testAuthoritiesNotNull() {
		authoritiesService = new AuthoritiesService();
		authorities = authoritiesService.getAuthorities();
		assertNotNull(authorities);
	}
	
	@Test
	public void testAuthoritiesNotZero() {
		authoritiesService = new AuthoritiesService();
		authorities = authoritiesService.getAuthorities();
		assertNotEquals(0, authorities.size());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testAuthoritiesWithWrongVersionThrowsException() {
		authoritiesService = new AuthoritiesService(WRONG_VERSION, AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testAuthoritiesWithWrongUriThrowsException() {
		authoritiesService = new AuthoritiesService(VERSION, WRONG_AUTHORITIES_URI);
		authorities = authoritiesService.getAuthorities();
	}
}
