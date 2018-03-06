package ratings.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ratings.exceptions.NotFoundException;
import ratings.services.RatingsService;

import static ratings.controllers.utils.ControllerUtils.createModelAndView;

@Controller
public class RatingsController {

    public static final String VIEW_NAME = "ratings";

    private final RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/foodhygiene/authorities/{authorityId}/ratings")
    public String getRatingsSummary(
            @PathVariable long authorityId,
            Model model) {
        model.addAttribute("authorityId", authorityId);
        model.addAttribute("ratings", ratingsService.getRatingSummaryForAuthority(authorityId));

        return VIEW_NAME;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView authourityNotFound(Exception exception) {
        return createModelAndView(exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView authourityNotNumeric(Exception exception) {
        return createModelAndView(exception, HttpStatus.BAD_REQUEST);
    }
}


