package com.allane.leasecontractadministrator.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration for Spring Security.
 */
@Configuration
public class SecurityConfig {


  /**
   * Configures the HTTP security for the application.
   *
   * @param http the HttpSecurity object to configure
   * @return the SecurityFilterChain object representing the configured security filter chain
   * @throws Exception if an error occurs during configuration
   */


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authz) -> authz
            .anyRequest().authenticated()
        )
        .httpBasic(withDefaults());
    return http.csrf().disable().build();
  }

}