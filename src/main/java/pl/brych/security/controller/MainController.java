package pl.brych.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.brych.security.dto.AppUser;
import pl.brych.security.repository.AppRepository;

@Controller
@AllArgsConstructor
public class MainController {

    private AppRepository appRepository;
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/singup")
    public ModelAndView singup() {
        return new ModelAndView("registration", "user", new AppUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appRepository.save(user);
        return new ModelAndView("redirect:/login");
    }
}
