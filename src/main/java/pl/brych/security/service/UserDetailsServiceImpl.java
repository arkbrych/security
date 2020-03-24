package pl.brych.security.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.brych.security.repository.AppRepository;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppRepository appRepository;

    public UserDetailsServiceImpl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appRepository.findByUsername(s);
    }
}
