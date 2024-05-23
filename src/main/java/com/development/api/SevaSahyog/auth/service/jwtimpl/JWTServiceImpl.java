package com.development.api.SevaSahyog.auth.service.jwtimpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.development.api.SevaSahyog.auth.service.JWTService;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
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
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*24*30))
                .signWith(SignatureAlgorithm.HS256, getSigninKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigninKey()).parseClaimsJws(token).getBody();
    }

    private Key getSigninKey() {
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET_KEY_BASE64);
        return new SecretKeySpec(apiKeySecretBytes, io.jsonwebtoken.SignatureAlgorithm.HS256.getJcaName());
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
