package ratings.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}

