package com.gestortareas.gestor_de_proyector.service;

import java.util.List;

public interface ICRUD<T, ID> {

    T crear(T entity) throws Exception;

    T actualizar(T entity) throws Exception;

    void eliminar(ID id) throws Exception;

    T obtenerPorId(ID id) throws Exception;

    List<T> obtenerTodos() throws Exception;
}