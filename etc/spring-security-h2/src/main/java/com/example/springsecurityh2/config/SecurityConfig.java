package com.example.springsecurityh2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.springsecurityh2.config.UserAuthorities.AIRCRAFT_BOARD;
import static com.example.springsecurityh2.config.UserAuthorities.AIRCRAFT_FLY;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  // fix: Whitelabel Error Page /h2-console
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            // The response will contain XSRF-TOKEN's cookie.
            // in development this should be disabled because to login you need to
            // GET the CSRF Token by hitting any endpoint
            // Login by hitting POST endpoint /login with form-data key `username`, `password`, `_csrf`
            // And, to logout by hitting POST endpoint /logout with form-data key `_csrf`.
//            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()

            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/v1/hello/").hasAuthority(AIRCRAFT_BOARD.getAuthority()) // pilot-client, deadhead-client, passenger-client
            .antMatchers("/api/v1/hello/location").hasAuthority(AIRCRAFT_FLY.getAuthority()) // pilot-client, deadhead-client
            .anyRequest().authenticated()
            .and()

//            // login with request header Authorization Basic with encoded base64. The password is in plain text
//            // can't logout
//            .httpBasic()

            // login with form-data on POST endpoint /login
            // can logout on GET endpoint /logout
            // can add key 'remember-me' with value 'on'
            // by default the session either expired after 30 minutes of inactivity or if the browser is closed.
            // the 'remember-me' cookie helps user get 'JSESSIONID' cookie without prompting login.
            .formLogin()
//            .defaultSuccessUrl("/api/v1/hello/", true) // force redirect after successful login
            .and()

            // 'remember-me' cookie value is `base64(username + : + expirationTime + : + md5Hex(username + : + expirationTime + : + password + : + key))
            .rememberMe();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
