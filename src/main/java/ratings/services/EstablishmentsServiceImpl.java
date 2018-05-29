package ratings.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ratings.model.Establishment;
import ratings.model.EstablishmentResponse;

import java.util.List;

public class EstablishmentsServiceImpl implements EstablishmentsService {
	private final HttpHeaders headers;
	private final UriComponentsBuilder builder;
	private final RestTemplate restTemplate;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public EstablishmentsServiceImpl(HttpHeaders headers, UriComponentsBuilder builder, RestTemplate restTemplate) {
		this.headers = headers;
		this.builder = builder;
		this.restTemplate = restTemplate;
	}


	@Override
	public List<Establishment> getEstablishmentsInAuthority(long authorityId) {
		String url = builder.buildAndExpand(authorityId).toString();

		logger.debug("Making call to external URL: " + url);
        ResponseEntity<EstablishmentResponse> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				EstablishmentResponse.class);
        return response.getBody().getEstablishments();
	}
}
