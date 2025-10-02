package com.gestor_de_proyectos.service.impl;

import com.gestor_de_proyectos.entity.Proyecto;
import com.gestor_de_proyectos.repository.ProyectoRepo;
import com.gestor_de_proyectos.service.IProyectoService;
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
