package pl.brych.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.brych.security.dto.AppUser;
import pl.brych.security.dto.VerificationToken;
import pl.brych.security.repository.AppRepository;
import pl.brych.security.repository.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private AppRepository appRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    public void addNewUser(AppUser user, HttpServletRequest request) {
        // Save user to repository
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appRepository.save(user);

        // Save token to repository
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

        // Generated token
        String url = "http://" + request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;
        try {
            mailSenderService.sendMail(user.getUsername(),
                    "Verification Token",
                    url,
                    false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void verifyToken(String token) {
        AppUser appUser = verificationTokenRepo.findByValue(token).getAppUser();
        appUser.setEnabled(true);
        appRepository.save(appUser);
    }
}
