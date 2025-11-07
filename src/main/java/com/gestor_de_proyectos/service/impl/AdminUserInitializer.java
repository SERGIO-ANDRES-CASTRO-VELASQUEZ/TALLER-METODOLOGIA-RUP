package com.gestor_de_proyectos.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gestor_de_proyectos.entity.User;
import com.gestor_de_proyectos.entity.enums.Role;
import com.gestor_de_proyectos.repository.UserRepo;

@Component
public class AdminUserInitializer {
	
	@Bean
	public CommandLineRunner createAdminUser(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepo.findByEmail("admin@mail.com").isEmpty()) {
				User admin = new User();
				admin.setFirstname("Jose Ricardo");
				admin.setLastname("Castañeda Losada");
				admin.setEmail("admin@mail.com");
				admin.setPassword(passwordEncoder.encode("admin1234"));
				admin.setRole(Role.ADMIN);
				
				userRepo.save(admin);
			}
			
			if (userRepo.findByEmail("user@mail.com").isEmpty()) {
				User user = new User();
				user.setFirstname("Maria del mar");
				user.setLastname("vaquiro");
				user.setEmail("user@mail.com");
				user.setPassword(passwordEncoder.encode("user1234"));
				user.setRole(Role.USER);
				
				userRepo.save(user);
			}
		};
	}

}
