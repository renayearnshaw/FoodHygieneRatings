package ratings.service;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import ratings.model.Establishment;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class EstablishmentsServiceIT {
	private static final int AUTHORITY_ID = 197;
	private static final String VERSION_KEY = "x-api-version";
	private static final String VERSION_VALUE = "2";
	private static final String WRONG_VERSION_VALUE = "1";
	private static final String ESTABLISHMENTS_URI = "http://api.ratings.food.gov.uk/Establishments?localAuthorityId=%d&pageSize=0";
	private static final String WRONG_ESTABLISHMENTS_URI = "http://api.ratings.food.gov.uk/EstablishmentsXXX?localAuthorityId=%d&pageSize=0";
	private List<Establishment> establishments;
	private static EstablishmentsService establishmentsService;
	
	@Test
	public void testEstablishmentsNotNull() {
		establishmentsService = new EstablishmentsServiceImpl(VERSION_KEY, VERSION_VALUE, ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
		assertNotNull(establishments);
	}
	
	@Test
	public void testEstablishmentsNotZero() {
		establishmentsService = new EstablishmentsServiceImpl(VERSION_KEY, VERSION_VALUE, ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
		assertNotEquals(0, establishments.size());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testEstablishmentsWithWrongVersionThrowsException() {
		establishmentsService = new EstablishmentsServiceImpl(VERSION_KEY, WRONG_VERSION_VALUE, ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testEstablishmentsWithWrongUriThrowsException() {
		establishmentsService = new EstablishmentsServiceImpl(VERSION_KEY, VERSION_VALUE, WRONG_ESTABLISHMENTS_URI);
		establishments = establishmentsService.getEstablishmentsInAuthority(AUTHORITY_ID);
	}
}
