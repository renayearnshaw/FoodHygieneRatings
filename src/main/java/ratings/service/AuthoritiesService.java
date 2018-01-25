package ratings.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ratings.model.Authority;
import ratings.model.AuthorityResponse;

@Service
public class AuthoritiesService {
	
	private static final String AUTHORITIES_URI = "http://api.ratings.food.gov.uk/Authorities/basic";
	private static final String X_API_VERSION = "x-api-version";
	private HttpHeaders headers = new HttpHeaders();
	private String uri;

	public AuthoritiesService() {
		headers.set(X_API_VERSION, "2");
		uri = AUTHORITIES_URI;
	}
	
	public AuthoritiesService(String version, String uri) {
		this.headers.set(X_API_VERSION, version);
		this.uri = uri;
	}
	
	public List<Authority> getAuthorities() {
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthorityResponse> response = restTemplate.exchange(
				uri, 
				HttpMethod.GET,
				new HttpEntity<>(headers),
				AuthorityResponse.class);
        return response.getBody().getAuthorities();
	}
}
