package pl.brych.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class EndpointController {

    @GetMapping("/forAll")
    public String forAll() {
        return "You have been logout";
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal) {
        return "You are logged as " + principal.getName() + " ";
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal) {
        return "You are logged as " + principal.getName() + " ";
    }


}
