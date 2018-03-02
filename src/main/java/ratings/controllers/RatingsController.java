package ratings.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ratings.services.RatingsService;

@Controller
public class RatingsController {

    private final RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/foodhygiene/authorities/{authorityId}/ratings")
    public String getRatingsSummary(
            @PathVariable Long authorityId,
            Model model) {
        model.addAttribute("authorityId", authorityId);
        model.addAttribute("ratings", ratingsService.getRatingSummaryForAuthority(authorityId));

        return "ratings";
    }
}


