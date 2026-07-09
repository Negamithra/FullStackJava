package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(auth -> auth

                // ==========================
                // Public APIs
                // ==========================

                .requestMatchers(
                        "/auth/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html")
                .permitAll()

                // ==========================
                // Platform Admin
                // ==========================

                .requestMatchers("/users/**")
                .hasRole("PLATFORM_ADMIN")

                // ==========================
                // Organizations
                // ==========================

                .requestMatchers(HttpMethod.GET,
                        "/organizations/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR",
                        "VOLUNTEER")

                .requestMatchers(HttpMethod.POST,
                        "/organizations/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR")

                .requestMatchers(HttpMethod.PUT,
                        "/organizations/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR")

                .requestMatchers(HttpMethod.DELETE,
                        "/organizations/**")
                .hasRole("PLATFORM_ADMIN",
                         "ORGANIZATION_COORDINATOR")

                // ==========================
                // Opportunities
                // ==========================

                .requestMatchers(HttpMethod.GET,
                        "/opportunities/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR",
                        "VOLUNTEER")

                .requestMatchers(HttpMethod.POST,
                        "/opportunities/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR")

                .requestMatchers(HttpMethod.PUT,
                        "/opportunities/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR")

                .requestMatchers(HttpMethod.DELETE,
                        "/opportunities/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "ORGANIZATION_COORDINATOR")

                // ==========================
                // Volunteer Enrollments
                // ==========================

                .requestMatchers("/volunteer-enrollments/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "VOLUNTEER")

                // ==========================
                // Impact Reports
                // ==========================

                .requestMatchers("/impact-reports/**")
                .hasAnyRole(
                        "PLATFORM_ADMIN",
                        "VOLUNTEER")

                // ==========================
                // Everything Else
                // ==========================

                .anyRequest()
                .authenticated())

            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
