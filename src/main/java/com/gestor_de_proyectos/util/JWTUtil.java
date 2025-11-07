package com.gestor_de_proyectos.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	public final String SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	private final long EXPIRATION_TIME = 1000*60*60; // 1 hora
	
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}
	
	private Claims extractClaims(String token) {
		return Jwts
		        .parser()
		        .verifyWith(key)
		        .build()
		        .parseSignedClaims(token)
		        .getPayload();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token).getExpiration();
	}
	
	public boolean validateToken(String username, UserDetails userDetails, String token) {
		
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

}
