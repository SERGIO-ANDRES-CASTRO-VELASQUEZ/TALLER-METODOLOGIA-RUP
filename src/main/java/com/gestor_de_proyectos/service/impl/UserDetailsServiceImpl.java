package com.gestor_de_proyectos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestor_de_proyectos.dto.RegisterRequest;
import com.gestor_de_proyectos.dto.UserResponse;
import com.gestor_de_proyectos.entity.User;
import com.gestor_de_proyectos.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepo userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;


	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
	
	public UserResponse registerUser(RegisterRequest registerUserRequest) {
		
		if(userRepository.findByEmail(registerUserRequest.getEmail()).isPresent()) {
			throw new RuntimeException("User Already Exist");
		}
		
		User user = new User();
		user.setFirstname(registerUserRequest.getFirstname());
		user.setLastname(registerUserRequest.getLastname());
		user.setEmail(registerUserRequest.getEmail());
		user.setRole(registerUserRequest.getRole());
		user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
		
		userRepository.save(user);
		
		UserResponse dto = mapper.map(user, UserResponse.class);
		
		return dto;
		
	}
}