package ratings.service;

import java.util.Map;

public interface RatingsService {
    Map<String, String> getRatingSummaryForAuthority(long authorityId);
}
