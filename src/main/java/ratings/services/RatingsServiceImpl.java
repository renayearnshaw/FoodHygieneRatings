package ratings.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ratings.exceptions.NotFoundException;
import ratings.model.Establishment;
import ratings.model.Rating;

@Service
public class RatingsServiceImpl implements RatingsService {

	private final EstablishmentsService establishmentsService;

	public RatingsServiceImpl(EstablishmentsService establishmentsService) {
		this.establishmentsService = establishmentsService;
	}

	@Override
	public Map<String, String> getRatingSummaryForAuthority(long authorityId) {
		
		List<Establishment> establishmentsInAuthority = establishmentsService.getEstablishmentsInAuthority(authorityId);

		if (establishmentsInAuthority.isEmpty()) {
			throw new NotFoundException(String.format("No establishments found for authority ID \'%d\'", authorityId));
		}

		long totalCount = establishmentsInAuthority.parallelStream().count();
		
		return ratingsAsPercentage(establishmentsInAuthority, totalCount);
	}

	private Map<String, String> ratingsAsPercentage(List<Establishment> establishmentsInAuthority, long totalCount) {
		Map<Rating, Long> ratingsByTotal = establishmentsInAuthority.parallelStream()
				.collect(Collectors.groupingBy(Establishment::getRating, Collectors.counting()));
		Map<String, String> ratingsByPercentage = ratingsByTotal.entrySet().parallelStream().collect(
				Collectors.toMap(e -> e.getKey().toString(), e -> String.format("%,.2f%%", e.getValue() * 100.0f / totalCount)));
		return ratingsByPercentage;
	}
}
