package pl.brych.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // saved password to database as encrypted charSequence
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User userAdmin = new User("admin",
                getPasswordEncoder().encode("admin123"),
                true,
                true,
                true,
                true,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/forUser").hasAnyRole("USER", "ADMIN")
                .antMatchers("/singup").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/forUser").permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().logoutSuccessUrl("/forAll");

    }
}
