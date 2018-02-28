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
	
	private final HttpHeaders headers = new HttpHeaders();
	private final String uri;
	private final RestTemplate restTemplate;

	public AuthoritiesServiceImpl(String apiVersionKey, String apiVersionValue, int pageNumber, int pageSize, String uri, RestTemplate restTemplate) {
		headers.set(apiVersionKey, apiVersionValue);
		this.uri = String.format("%s/%d/%d", uri, pageNumber, pageSize);
		this.restTemplate = restTemplate;
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
