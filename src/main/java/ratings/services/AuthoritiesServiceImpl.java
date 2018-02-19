package ratings.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ratings.model.Authority;
import ratings.model.AuthorityResponse;

public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	private HttpHeaders headers = new HttpHeaders();
	private String uri;
	private RestTemplate restTemplate = new RestTemplate();

	public AuthoritiesServiceImpl(String apiVersionKey, String apiVersionValue, String uri) {
		headers.set(apiVersionKey, apiVersionValue);
		this.uri = uri;
	}

	@Override
	public List<Authority> getAuthorities() {
        ResponseEntity<AuthorityResponse> response = restTemplate.exchange(
				uri, 
				HttpMethod.GET,
				new HttpEntity<>(headers),
				AuthorityResponse.class);
        return response.getBody().getAuthorities();
	}
}
