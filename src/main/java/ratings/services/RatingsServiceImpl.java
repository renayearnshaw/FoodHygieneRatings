package ratings.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ratings.exceptions.NotFoundException;
import ratings.model.Establishment;
import ratings.model.Rating;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class RatingsServiceImpl implements RatingsService {

    private final EstablishmentsService establishmentsService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RatingsServiceImpl(EstablishmentsService establishmentsService) {
        this.establishmentsService = establishmentsService;
    }

    @Override
    public Map<Rating, String> getRatingSummaryForAuthority(long authorityId) {

        List<Establishment> establishmentsInAuthority = establishmentsService.getEstablishmentsInAuthority(authorityId);

        if (establishmentsInAuthority.isEmpty()) {
            String message = String.format("No establishments found for authority ID \'%d\'", authorityId);
            logger.error(message);
            throw new NotFoundException(message);
        }

        return ratingsAsPercentage(establishmentsInAuthority);
    }

    private Map<Rating, String> ratingsAsPercentage(List<Establishment> establishmentsInAuthority) {
        Map<Rating, Long> ratingsByTotal = establishmentsInAuthority.parallelStream()
                .collect(groupingBy(Establishment::getRating, counting()));

        long totalCount = ratingsByTotal.values().stream().mapToLong(Long::longValue).sum();

        return ratingsByTotal
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        toMap(
                                Map.Entry::getKey,
                                e -> String.format("%,.2f%%", e.getValue() * 100.0f / totalCount),
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new));
    }
}
