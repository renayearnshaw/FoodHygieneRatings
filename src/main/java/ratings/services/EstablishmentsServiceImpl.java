package ratings.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;
import ratings.model.Establishment;
import ratings.model.EstablishmentResponse;

public class EstablishmentsServiceImpl implements EstablishmentsService {
	private final HttpHeaders headers = new HttpHeaders();
	private final UriComponentsBuilder builder;
	private final RestTemplate restTemplate;

	public EstablishmentsServiceImpl(String apiVersionKey, String apiVersionValue, UriComponentsBuilder builder, RestTemplate restTemplate) {
		headers.set(apiVersionKey, apiVersionValue);
		this.builder = builder;
		this.restTemplate = restTemplate;
	}


	@Override
	public List<Establishment> getEstablishmentsInAuthority(long authorityId) {
        ResponseEntity<EstablishmentResponse> response = restTemplate.exchange(
				builder.buildAndExpand(authorityId).toString(),
				HttpMethod.GET,
				new HttpEntity<>(headers),
				EstablishmentResponse.class);
        return response.getBody().getEstablishments();
	}
}
