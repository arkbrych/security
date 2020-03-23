package pl.brych.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    @GetMapping("/forAll")
    public String forAll() {
        return "forAll";
    }

    @GetMapping("/forUser")
    public String forUser() {
        return "forUser";
    }

    @GetMapping("/forAdmin")
    public String forAdmin() {
        return "forAdmin";
    }


}
