package com.gestor_de_proyectos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gestor_de_proyectos.entity.enums.Permissions;
import com.gestor_de_proyectos.entity.enums.Role;
import com.gestor_de_proyectos.filters.JwtAuthFilter;
import com.gestor_de_proyectos.service.impl.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity // Indica a la app spring que se va a modificar la configuracion de seguridad y que vamos a habilitar nuestras funciones de seguridad web dentro de la app.
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
        		.authorizeHttpRequests(auth->
        				auth.requestMatchers("/authenticate").permitAll()
        						.requestMatchers("/api/users/register").permitAll()
        						//.requestMatchers("/api/proyectos").hasRole(Role.ADMIN.name())
        						.requestMatchers(HttpMethod.GET, "/api/proyectos/**").hasAuthority(Permissions.PROYECTO_READ.name())
        						.requestMatchers(HttpMethod.POST, "/api/proyectos/**").hasAuthority(Permissions.PROYECTO_WRITE.name())
        						.requestMatchers(HttpMethod.DELETE, "/api/proyectos/**").hasAuthority(Permissions.PROYECTO_DELETE.name())
        						.anyRequest().authenticated());
        		//.httpBasic(withDefaults());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	/*
	 * @Bean public UserDetailsService userDetailsService() { return new
	 * UserDetailsServiceImpl(); }
	 */
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, 
    												   PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
                
        return new ProviderManager(provider);
    }

}