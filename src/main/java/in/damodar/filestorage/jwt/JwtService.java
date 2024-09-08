package in.damodar.filestorage.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import in.damodar.filestorage.nativeuser.User;
import in.damodar.filestorage.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.*;
import java.time.temporal.*;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.service.signingkey}")
    private String secretKey;

    private final CustomUserDetailsService customUserDetailsService;

    public String issueToken(UserDetails userDetails) {
        var user = (User) userDetails;
        List<String> authorities = userDetails.getAuthorities().stream().map(a -> a.toString()).toList();
        return JWT.create()
        .withSubject(user.getUsername())
        .withClaim("authorities", authorities)
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
        .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT decodedJWT(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey))
        .build().verify(token);
    }

    public UserDetails convertToUserDetails(DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject().toString();
        var user = customUserDetailsService.loadUserByUsername(username);
        return user;
    }
    

}
