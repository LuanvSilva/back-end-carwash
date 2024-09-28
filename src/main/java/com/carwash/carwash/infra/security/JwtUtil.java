package com.carwash.carwash.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carwash.carwash.util.constantes.Constantes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    private Algorithm algorithm = Algorithm.HMAC256(Constantes.SECRET_KEY_STRING);

    public String generateToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername(), 5); // Token expira em 5 horas
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername(), 10); // Refresh token expira em 10 horas
    }

    private String createToken(Map<String, Object> claims, String subject, int hours) {
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * hours))
                .sign(algorithm);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    public <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        final DecodedJWT decodedJWT = extractAllClaims(token);
        return claimsResolver.apply(decodedJWT);
    }

    private DecodedJWT extractAllClaims(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token); // Verifica a validade e decodifica o token
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);
    }
}
