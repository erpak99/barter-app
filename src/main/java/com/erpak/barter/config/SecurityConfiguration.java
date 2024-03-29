package com.erpak.barter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.erpak.barter.enums.Permission.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAnyRole("ADMIN","USER")


                                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/brands/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/brands/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/v1/barters/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/v1/barters/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/barters/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/barters/**").hasAnyRole("ADMIN","USER")

                                .requestMatchers("api/v1/users/**").permitAll()


                        .requestMatchers(GET, "api/v1/categories/**").permitAll()
                        .requestMatchers(POST, "api/v1/categories/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "api/v1/categories/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "api/v1/categories/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers(GET, "api/v1/brands/**").permitAll()
                        .requestMatchers(POST, "api/v1/brands/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "api/v1/brands/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "api/v1/brands/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers(GET, "api/v1/products/**").permitAll()
                        .requestMatchers(POST, "api/v1/products/**").hasAuthority(USER_CREATE.name())
                        .requestMatchers(PUT, "api/v1/products/**").hasAnyAuthority(ADMIN_UPDATE.name(),USER_UPDATE.name())
                        .requestMatchers(DELETE, "api/v1/products/**").hasAnyAuthority(ADMIN_DELETE.name(),USER_DELETE.name())


                        .requestMatchers(GET, "api/v1/barters/**").hasAnyAuthority(ADMIN_READ.name(),USER_READ.name())
                        .requestMatchers(POST, "api/v1/barters/**").hasAuthority(USER_CREATE.name())
                        .requestMatchers(PUT, "api/v1/barters/**").hasAnyAuthority(USER_UPDATE.name())
                        .requestMatchers(DELETE, "api/v1/barters/**").hasAnyAuthority(ADMIN_DELETE.name(),USER_DELETE.name())


                        .anyRequest().authenticated()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }


}
