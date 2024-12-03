package fr.fdr.api_transport.security.jwt;

import fr.fdr.api_transport.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${api-transport.jwtSecret}")
    private String jwtSecret;

    @Value("${api-transport.jwtExpirationMs}")
    private int jwtExpirationInMs;

    public String generateJwtToken(Authentication authentication) {
       UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
       return Jwts.builder()
               .setSubject(userPrincipal.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
               .signWith(key(), SignatureAlgorithm.HS256)
               .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key()).build().parse(token);
            return true;
        }catch (MalformedJwtException e){
            System.out.println("Invalid JWT token" + e.getMessage());
        }catch (ExpiredJwtException e){
            System.out.printf("Jwt token is expired" + e.getMessage());
        }catch (UnsupportedJwtException e){
            System.out.printf("JWT token is unsupported" + e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Jwt token contains invalid characters" + e.getMessage());
        }

        return false;
    }
}
