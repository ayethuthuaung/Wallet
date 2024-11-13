package com.training.dat.Wallet.config;

import com.training.dat.Wallet.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                		authz -> authz
                		 .requestMatchers("/api/auth/login", "/swagger-ui.html" ,"swagger-ui/**", "/v3/api-docs/**").permitAll() // Public endpoints
                         //.requestMatchers("/api/investor-account-list", "/api/investor-account/**", "/api/investor-account", "/api/investor-account/*", "/api/investor-account/**")
                		 .anyRequest().authenticated() // Protect this endpoint
                )
                //.requestMatchers("/api/auth/login").permitAll() // Public endpoints
                //.requestMatchers("/investor-account-list").authenticated() // Protect this endpoint
                //.and()
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
