package ratings.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import ratings.model.Authority;
import ratings.model.AuthorityResponse;

import java.util.List;

public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	private final HttpHeaders headers = new HttpHeaders();
	private final UriComponents uriComponents;
	private final RestTemplate restTemplate;

	public AuthoritiesServiceImpl(String apiVersionKey, String apiVersionValue, UriComponents uriComponents, RestTemplate restTemplate) {
		headers.set(apiVersionKey, apiVersionValue);
		this.uriComponents = uriComponents;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Authority> getAuthorities() {
        ResponseEntity<AuthorityResponse> response = restTemplate.exchange(
				uriComponents.toString(),
				HttpMethod.GET,
				new HttpEntity<>(headers),
				AuthorityResponse.class);
        return response.getBody().getAuthorities();
	}
}
