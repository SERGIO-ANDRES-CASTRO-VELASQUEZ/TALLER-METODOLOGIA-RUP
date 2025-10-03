package com.gestor_de_proyectos.service;

import com.gestor_de_proyectos.dto.ProyectoDTO;
import com.gestor_de_proyectos.entity.Proyecto;

public interface IProyectoService extends ICRUD<Proyecto, Long>{
	Proyecto crear(ProyectoDTO dto);
}
