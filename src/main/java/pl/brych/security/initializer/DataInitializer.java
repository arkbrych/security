package pl.brych.security.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.brych.security.dto.AppUser;
import pl.brych.security.repository.AppRepository;

@Component
public class DataInitializer {

    private PasswordEncoder passwordEncoder;

    private AppRepository appRepository;

    @Autowired
    public DataInitializer(PasswordEncoder passwordEncoder, AppRepository appRepository){
        this.passwordEncoder = passwordEncoder;
        this.appRepository = appRepository;

        AppUser appUser = new AppUser();
        appUser.setUsername("Arkadiusz");
        appUser.setPassword(passwordEncoder.encode("admin123"));
        appRepository.save(appUser);
    }
}