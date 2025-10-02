package com.gestor_de_proyectos.repository;

import com.gestor_de_proyectos.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {

}
