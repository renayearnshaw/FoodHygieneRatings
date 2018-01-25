package ratings.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ratings.model.Establishment;
import ratings.model.EstablishmentResponse;

@Service
public class EstablishmentsService {
	private static final String ESTABLISHMENTS_URI = "http://api.ratings.food.gov.uk/Establishments?localAuthorityId=%d&pageSize=0";
	private static final String X_API_VERSION = "x-api-version";
	private HttpHeaders headers = new HttpHeaders();
	private String uri;

	public EstablishmentsService() {
		headers.set(X_API_VERSION, "2");
		uri = ESTABLISHMENTS_URI;
	}
	
	public EstablishmentsService(String version, String uri) {
		this.headers.set(X_API_VERSION, version);
		this.uri = uri;
	}
	
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
