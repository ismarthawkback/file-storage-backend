package in.damodar.filestorage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS
        ));
       
        http
            .authorizeHttpRequests(auth -> 
               {
                auth.requestMatchers("/hello").authenticated();
                auth.requestMatchers("/").permitAll();
               }
            );
        http.formLogin(login -> login.disable());
        http.logout(logout -> logout.disable());
        return http.build();
    }

}
