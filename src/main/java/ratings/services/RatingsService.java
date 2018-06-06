package ratings.services;

import ratings.model.Rating;

import java.util.Map;

public interface RatingsService {
    Map<Rating, String> getRatingSummaryForAuthority(long authorityId);
}
