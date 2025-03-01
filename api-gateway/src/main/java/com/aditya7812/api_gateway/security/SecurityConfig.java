package com.aditya7812.api_gateway.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

   /*  @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception{
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/api/auth/**").permitAll()   
                        .requestMatchers("/api/public/**").permitAll() 
                        .requestMatchers("/eureka/**").permitAll()
                        .anyRequest().authenticated()           
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/

    @Bean
    public CorsConfigurationSource corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("https://5173-aditya7812-ecommercemic-h7ba9h2xbki.ws-us118.gitpod.io", "https://5174-aditya7812-ecommercemic-h7ba9h2xbki.ws-us118.gitpod.io", "https://5174-aditya7812-ecommercemic-5oxq92yh6rp.ws-us118.gitpod.io", "https://5173-aditya7812-ecommercemic-5oxq92yh6rp.ws-us118.gitpod.io")); // Change to your frontend URL
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
