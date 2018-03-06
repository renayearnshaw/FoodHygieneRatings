package ratings.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import ratings.services.AuthoritiesService;

@Controller
public class AuthoritiesController {

    private final AuthoritiesService authoritiesService;

    public AuthoritiesController(AuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/foodhygiene/authorities")
    public String getAuthorities(Model model) {

        model.addAttribute("authorities", authoritiesService.getAuthorities());

        return "authorities";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView badVersionOrURI(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("errorView");
        modelAndView.addObject("httpStatus", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}

