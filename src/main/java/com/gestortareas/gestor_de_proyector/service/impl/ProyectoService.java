package com.gestortareas.gestor_de_proyector.service.impl;

import com.gestortareas.gestor_de_proyector.entity.Estudiante;
import com.gestortareas.gestor_de_proyector.entity.Proyecto;
import com.gestortareas.gestor_de_proyector.repository.ProyectoRepo;
import com.gestortareas.gestor_de_proyector.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService extends CRUDImpl<Proyecto, Long> implements IProyectoService {

    @Autowired
    private ProyectoRepo repo;

    @Override
    protected JpaRepository<Proyecto, Long> getRepo() {
        return repo;
    }

}
