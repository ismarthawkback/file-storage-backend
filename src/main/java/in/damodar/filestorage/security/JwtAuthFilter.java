package in.damodar.filestorage.security;

import java.io.IOException;

import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization");
                if(authHeader == null) {
                    System.out.println("No Authentication Header");
                    filterChain.doFilter(request, response);
                }
        // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }
    
}
