package ratings.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ratings.model.Authority;
import ratings.services.AuthoritiesService;
import ratings.services.RatingsService;

@RestController
@RequestMapping("/foodhygiene/authorities")
public class FoodHygieneRatingsController {
	
    private final AuthoritiesService authoritiesService;
	private final RatingsService ratingsService;

    public FoodHygieneRatingsController(AuthoritiesService authoritiesService, RatingsService ratingsService) {
        this.authoritiesService = authoritiesService;
        this.ratingsService = ratingsService;
    }

    @RequestMapping()
    public List<Authority> getAuthorities() {
        return authoritiesService.getAuthorities();
    }
	
	@RequestMapping("/{authorityId}/ratings/summary")
    public Map<String, String> getRatingsSummary(@PathVariable long authorityId) {
        return ratingsService.getRatingSummaryForAuthority(authorityId);
	}
}
