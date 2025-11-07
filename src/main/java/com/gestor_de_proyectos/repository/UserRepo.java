package com.gestor_de_proyectos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor_de_proyectos.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}