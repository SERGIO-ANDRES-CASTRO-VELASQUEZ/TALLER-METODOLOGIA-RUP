package com.gestor_de_proyectos.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestor_de_proyectos.dto.AuthRequest;
import com.gestor_de_proyectos.util.JWTUtil;

@RestController
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authRequest.getUsername(), 
							authRequest.getPassword())
			);
			
			String token = jwtUtil.generateToken(authRequest.getUsername());
			Date expiration = jwtUtil.extractExpiration(token);
			
			return ResponseEntity.ok(new AuthResponse(token, expiration));
		}catch(Exception e) {
			throw e;
		}
	}
}
