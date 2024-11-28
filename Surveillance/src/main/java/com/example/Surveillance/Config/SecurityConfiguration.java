package com.example.Surveillance.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()

                        // Management endpoints for SuperAdmin and AdminEtablissement
                        .requestMatchers("/api/v1/management/**").hasAnyRole("SUPERADMIN", "ADMIN_ETABLISSEMENT")
                        .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(
                                "SUPERADMIN_READ", "ADMIN_ETABLISSEMENT_READ")
                        .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(
                                "SUPERADMIN_CREATE", "ADMIN_ETABLISSEMENT_CREATE")
                        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(
                                "SUPERADMIN_UPDATE", "ADMIN_ETABLISSEMENT_UPDATE")
                        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(
                                "SUPERADMIN_DELETE", "ADMIN_ETABLISSEMENT_DELETE")

                        // Department endpoints for AdminDepartement
                        .requestMatchers("/api/v1/departments/**").hasRole("ADMIN_DEPARTEMENT")
                        .requestMatchers(GET, "/api/v1/departments/**").hasAuthority("ADMIN_DEPARTEMENT_READ")
                        .requestMatchers(POST, "/api/v1/departments/**").hasAuthority("ADMIN_DEPARTEMENT_CREATE")
                        .requestMatchers(PUT, "/api/v1/departments/**").hasAuthority("ADMIN_DEPARTEMENT_UPDATE")
                        .requestMatchers(DELETE, "/api/v1/departments/**").hasAuthority("ADMIN_DEPARTEMENT_DELETE")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        return http.build();
    }
}


