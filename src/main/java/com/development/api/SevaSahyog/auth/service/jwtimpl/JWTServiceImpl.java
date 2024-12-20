package com.development.api.SevaSahyog.auth.service.jwtimpl;

import com.development.api.SevaSahyog.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.development.api.SevaSahyog.auth.service.JWTService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
@Component
public class JWTServiceImpl implements JWTService {

    private static final String SECRET_KEY_BASE64 = "413F4428472B4B6250655368566D597089489UR8U493URE89";

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24*30))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Method to validate token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(getSigninKey()).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            throw new TokenExpiredException("Token has expired", e);
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
            final Claims claims = extractAllClaims(token);
            return claimsResolvers.apply(claims);
    }

    private Key getSigninKey() {
        byte[] apiKeySecretBytes = Decoders.BASE64.decode(SECRET_KEY_BASE64);
        return Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}

