package com.gestor_de_proyectos.repository;

import com.gestor_de_proyectos.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepo extends JpaRepository<Proyecto, Long> {
}
