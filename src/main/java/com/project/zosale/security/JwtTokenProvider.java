package com.project.zosale.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${zosaleapp.app.secret}")
    private String APP_SECRET;
    @Value("${zosaleapp.expires.in}")
    private long EXPIRES_IN;

    public String generateJwtToken(Authentication authentication){
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,APP_SECRET)
                .compact();
    }

    Long getUserIdFromJwt(String token){
        Claims claims   = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();

        // subject == id
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                IllegalArgumentException e){
            return false;
        }

    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();

        return expiration.before(new Date());
    }
}
