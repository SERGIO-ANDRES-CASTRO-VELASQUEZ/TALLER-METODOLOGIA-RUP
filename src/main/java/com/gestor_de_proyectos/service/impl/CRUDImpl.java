package com.gestor_de_proyectos.service.impl;

import com.gestor_de_proyectos.service.ICRUD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract JpaRepository<T,ID> getRepo();

    @Override
    public T crear(T entity) throws Exception {
        return getRepo().save(entity);
    }

    @Override
    public T actualizar(T entity) throws Exception {
        return getRepo().save(entity);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);
    }

    @Override
    public T obtenerPorId(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public List<T> obtenerTodos() throws Exception {
        return getRepo().findAll();
    }
}

