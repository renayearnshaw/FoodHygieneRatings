package ratings.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ratings.services.RatingsService;

import java.util.Map;

@RestController
@RequestMapping("/foodhygiene/authorities")
public class RatingsController {

	private final RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

	@RequestMapping("/{authorityId}/ratings")
    public Map<String, String> getRatingsSummary(@PathVariable long authorityId) {
        return ratingsService.getRatingSummaryForAuthority(authorityId);
	}
}
