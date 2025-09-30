package com.gestortareas.gestor_de_proyector.service.impl;


import com.gestortareas.gestor_de_proyector.entity.Estudiante;
import com.gestortareas.gestor_de_proyector.repository.EstudianteRepo;
import com.gestortareas.gestor_de_proyector.service.IEstudianteService;
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
