package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Configuration
// Option 2: Remove the Business Logic from the method.
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig
        // Option 3: handle it at Request Level
        extends WebSecurityConfigurerAdapter
{

  // Option 3: handle it at Request Level.
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests((auth) -> auth
                    .mvcMatchers("/location").hasAuthority("captain")
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer((oauth2) -> oauth2
                    .jwt(Customizer.withDefaults())
            );
  }

//  // Resource server depends on the authorization server to make authorization decision.
//  // Disable resource server before using this.
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails ria = User.withDefaultPasswordEncoder()
//            .username("ria")
//            .password("password")
//            .roles("USER")
//            .build();
//    UserDetails josh = User.withDefaultPasswordEncoder()
//            .username("josh")
//            .password("password")
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(ria, josh);
//  }

  @Bean
  public JwtAuthenticationConverter customJwtAuthenticationConverter() {
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
    return jwtAuthenticationConverter;
  }

  public static class CustomJwtGrantedAuthoritiesConverter
          implements Converter<Jwt, Collection<GrantedAuthority>> {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

//    // Option 1:
//    @Override
//    public Collection<GrantedAuthority> convert(Jwt jwt) {
//      Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
//      Collection<GrantedAuthority> updatedGrantedAuthorities = new ArrayList<>(grantedAuthorities);
//      Set<String> grantedAuthorityStrings = AuthorityUtils.authorityListToSet(grantedAuthorities);
//      if (grantedAuthorityStrings.contains("SCOPE_aircraft.fly") || grantedAuthorityStrings.contains("SCOPE_boat.sail")) {
//        updatedGrantedAuthorities.add(new SimpleGrantedAuthority("captain"));
//      }
//      return updatedGrantedAuthorities;
//    }

    // Option 2:
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
      Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
      Collection<String> scopes = jwt.getClaim("scope");
      if (scopes.contains("aircraft.fly") || scopes.contains("boat.sail")) {
        grantedAuthorities.add(new SimpleGrantedAuthority("captain"));
      }
      return grantedAuthorities;
    }
  }
}
