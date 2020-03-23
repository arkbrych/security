package pl.brych.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

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
