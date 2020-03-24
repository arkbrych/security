package pl.brych.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // saved password to database as encrypted charSequence
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        User userAdmin = new User("Admin",
//                getPasswordEncoder().encode("admin123"),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        User userUser = new User("user",
//                getPasswordEncoder().encode("user123"),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//        // user from database
//        // auth.jdbcAuthentication()
//        auth.inMemoryAuthentication().withUser(userAdmin);
//        auth.inMemoryAuthentication().withUser(userUser);

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/forUser").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/forAll");
    }
}
