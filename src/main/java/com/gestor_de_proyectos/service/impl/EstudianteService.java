package com.gestor_de_proyectos.service.impl;


import com.gestor_de_proyectos.entity.Estudiante;
import com.gestor_de_proyectos.repository.EstudianteRepo;
import com.gestor_de_proyectos.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService extends CRUDImpl<Estudiante, Long> implements IEstudianteService {

    @Autowired
    private EstudianteRepo repo;

    @Override
    protected JpaRepository<Estudiante, Long> getRepo() {
        return repo;
    }
}
