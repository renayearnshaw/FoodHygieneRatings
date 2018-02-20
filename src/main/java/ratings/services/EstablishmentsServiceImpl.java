package ratings.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ratings.model.Establishment;
import ratings.model.EstablishmentResponse;

public class EstablishmentsServiceImpl implements EstablishmentsService {
	private final HttpHeaders headers = new HttpHeaders();
	private final String uri;

	public EstablishmentsServiceImpl(String apiVersionKey, String apiVersionValue, String uri) {
		headers.set(apiVersionKey, apiVersionValue);
		this.uri = uri;
	}


	@Override
	public List<Establishment> getEstablishmentsInAuthority(long authorityId) {
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EstablishmentResponse> response = restTemplate.exchange(
				String.format(uri, authorityId), 
				HttpMethod.GET,
				new HttpEntity<>(headers),
				EstablishmentResponse.class);
        return response.getBody().getEstablishments();
	}
}
