package ratings.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ratings.model.Authority;
import ratings.services.AuthoritiesService;

import java.util.List;

@RestController
@RequestMapping("/foodhygiene/authorities")
public class AuthoritiesController {

    private final AuthoritiesService authoritiesService;

    public AuthoritiesController(AuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @RequestMapping()
    public List<Authority> getAuthorities() {
        return authoritiesService.getAuthorities();
    }

}
