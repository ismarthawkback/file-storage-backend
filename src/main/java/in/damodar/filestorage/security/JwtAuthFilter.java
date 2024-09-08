package in.damodar.filestorage.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import in.damodar.filestorage.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization");
                if(authHeader == null) {
                    System.out.println("No Authentication Header");
                    filterChain.doFilter(request, response);
                    return;
                }
                var bearerTokeString = extractBearerToken(authHeader);
                if(bearerTokeString == null) {
                    System.out.println("No Bearer Token : ");
                    filterChain.doFilter(request, response);
                    return;
                }
                try {

                    bearerTokeString.map(jwtService::decodedJWT)
                    .map(jwtService::convertToUserDetails)
                    .map(UserPrincipalAuthenticationToken::new)
                    .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
                    System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
                } catch(Exception ex) {
                    System.out.println("Signature verificaton Exceptoin " + ex.getMessage() + " " + ex.getClass().toString());
                }
        // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

    private Optional<String> extractBearerToken(String authHeader) {
        if(authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.substring(7));
        } 
        return null;
    }
    
}
