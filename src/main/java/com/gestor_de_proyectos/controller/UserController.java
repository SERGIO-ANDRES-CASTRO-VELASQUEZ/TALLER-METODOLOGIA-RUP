package com.gestor_de_proyectos.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor_de_proyectos.dto.RegisterRequest;
import com.gestor_de_proyectos.dto.UserResponse;
import com.gestor_de_proyectos.entity.enums.Role;
import com.gestor_de_proyectos.service.impl.UserDetailsServiceImpl;
import com.gestor_de_proyectos.util.JWTUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserDetailsServiceImpl service;
	private final JWTUtil jwtUtil;

	public UserController(UserDetailsServiceImpl service, JWTUtil jwtUtil) {
		this.service = service;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
		registerRequest.setRole(Role.USER);
		UserResponse user = service.registerUser(registerRequest);
		
		String token = jwtUtil.generateToken(user.getEmail());
		Date expiration = jwtUtil.extractExpiration(token);
		
		return ResponseEntity.ok(new AuthResponse(token, expiration));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/create")
	public ResponseEntity<UserResponse> registerByAdmin(@RequestBody RegisterRequest registerRequest){
		UserResponse user = service.registerUser(registerRequest);
		return ResponseEntity.ok(user);
	}
	
}
