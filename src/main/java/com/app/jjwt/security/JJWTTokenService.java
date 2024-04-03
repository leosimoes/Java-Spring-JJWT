package com.app.jjwt.security;

import com.app.jjwt.entities.JJWTUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JJWTTokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final String ISSUER = "auth-api";

    private final int EXPIRATION_HOURS = 2;

    private final String ZONE_OFFSET = "-03:00";

    public String generateToken(JJWTUser jjwtUser){
        return JWT.create()
                .withIssuer(this.ISSUER)
                .withSubject(jjwtUser.getLogin())
                .withExpiresAt(this.calculateExpiration())
                .sign(this.getAlgorithm());
    }

    public String validateToken(String token){
        return JWT.require(this.getAlgorithm())
                .withIssuer(this.ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant calculateExpiration(){
        return LocalDateTime.now()
                .plusHours(this.EXPIRATION_HOURS)
                .toInstant(ZoneOffset.of(this.ZONE_OFFSET));
    }

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(this.secret);
    }

}