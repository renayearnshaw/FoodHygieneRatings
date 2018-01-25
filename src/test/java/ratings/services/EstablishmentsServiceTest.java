package ratings.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import ratings.model.Establishment;
import ratings.service.EstablishmentsService;

public class EstablishmentsServiceTest {
	private static final int AUTHORITY_ID = 197;
	private static final String VERSION = "2";
	private static final String WRONG_VERSION = "1";
	private static final String ESTABLISHMENTS_URI = "http://api.ratings.food.gov.uk/Establishments?localAuthorityId=%d&pageSize=0";
	private static final String WRONG_ESTABLISHMENTS_URI = "http://api.ratings.food.gov.uk/EstablishmentsXXX?localAuthorityId=%d&pageSize=0";
	private List<Establishment> establishments;
	private static EstablishmentsService establishmentsService;
	
	@Test
	public void testEstablishmentsNotNull() {
		establishmentsService = new EstablishmentsService();
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
		assertNotNull(establishments);
	}
	
	@Test
	public void testEstablishmentsNotZero() {
		establishmentsService = new EstablishmentsService();
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
		assertNotEquals(0, establishments.size());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testEstablishmentsWithWrongVersionThrowsException() {
		establishmentsService = new EstablishmentsService(WRONG_VERSION, ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testEstablishmentsWithWrongUriThrowsException() {
		establishmentsService = new EstablishmentsService(VERSION, WRONG_ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
	}
}
