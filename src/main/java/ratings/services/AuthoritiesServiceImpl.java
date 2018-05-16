package ratings.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ratings.model.Authority;
import ratings.model.AuthorityResponse;

import java.util.List;

public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	private final HttpHeaders headers;
	private final UriComponentsBuilder builder;
	private final RestTemplate restTemplate;
	private int pageNumber;
	private int pageSize;

	public AuthoritiesServiceImpl(
			HttpHeaders headers,
			UriComponentsBuilder builder,
			RestTemplate restTemplate,
			int pageNumber,
			int pageSize) {
		this.headers = headers;
		this.builder = builder;
		this.restTemplate = restTemplate;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	@Override
	public List<Authority> getAuthorities() {
        ResponseEntity<AuthorityResponse> response = restTemplate.exchange(
				builder.buildAndExpand(pageNumber, pageSize).toString(),
				HttpMethod.GET,
				new HttpEntity<>(headers),
				AuthorityResponse.class);
        return response.getBody().getAuthorities();
	}
}
