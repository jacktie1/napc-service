package org.apathinternational.faithpathrestful.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // application.properties configuration
    @Value("${jwt.secret}") 
    private String jwtSecret; 

    // Token validity duration in seconds
    @Value("${jwt.token.validity-seconds}") 
    private long validityInSeconds;

    // Your implementation of user details service
    private final DatabaseUserDetailsService userDetailsService;

    public JwtTokenProvider(DatabaseUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInSeconds * 1000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        // Extract the token from the Authorization header
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token validation failed
            return false;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token).getBody();
        String userId = claims.getSubject();

        if (userId != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        return null;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}