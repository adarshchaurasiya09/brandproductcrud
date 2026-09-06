package com.example.brandproductcrud.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private  String secretKey ;
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(
						new Date(System.currentTimeMillis() + 1000 * 60 * 60)
						)
				.signWith(getSignKey())
				.compact();
	}
	
	  public String extractUsername(String token) {

	        return Jwts.parser()
	                .verifyWith(getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload()
	                .getSubject();
	    }

	    public boolean isTokenValid(
	            String token,
	            org.springframework.security.core.userdetails.UserDetails userDetails) {

	        String username = extractUsername(token);

	        return username.equals(userDetails.getUsername())
	                && !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {

	        Date expiration = Jwts.parser()
	                .verifyWith(getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload()
	                .getExpiration();

	        return expiration.before(new Date());
	    }

	private SecretKey getSignKey() { 
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}

