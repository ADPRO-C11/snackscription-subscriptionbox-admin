package id.ac.ui.cs.advprog.snackscription_subscriptionbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import id.ac.ui.cs.advprog.snackscription_subscriptionbox.utils.JWTUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTUtils jwtUtils;

    public SecurityConfig(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/actuator/prometheus").permitAll() // Allow unauthenticated access
                                .requestMatchers("/subscription-box/**", "/public/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JWTAuthFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
