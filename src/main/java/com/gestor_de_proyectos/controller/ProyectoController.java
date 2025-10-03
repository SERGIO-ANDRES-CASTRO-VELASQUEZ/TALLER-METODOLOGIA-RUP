package com.gestor_de_proyectos.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestor_de_proyectos.dto.ProyectoDTO;
import com.gestor_de_proyectos.entity.Proyecto;
import com.gestor_de_proyectos.exception.ModeloNotFoundException;
import com.gestor_de_proyectos.service.IProyectoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private IProyectoService service;

	@Autowired
	private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listarTodos() throws Exception{
    	List<ProyectoDTO> lista = service.obtenerTodos().stream().map(p -> mapper.map(p, ProyectoDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/buscar-proyecto/{id}")
    public ResponseEntity<ProyectoDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Proyecto obj = service.obtenerPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		ProyectoDTO dto = mapper.map(obj, ProyectoDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody ProyectoDTO dto) throws Exception {
		Proyecto obj = service.crear(dto);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

    @PutMapping("/actualizar-proyecto/{id}")
    public ResponseEntity<ProyectoDTO> actualizar(@Valid @RequestBody ProyectoDTO dto) throws Exception {
		Proyecto obj = service.obtenerPorId(dto.getId());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getId());
		}
		
		Proyecto p = mapper.map(dto, Proyecto.class);		
		Proyecto pActualizado = service.actualizar(p);
		ProyectoDTO dtoResponse = mapper.map(pActualizado, ProyectoDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

    @DeleteMapping("/eliminar-proyecto/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Proyecto obj = service.obtenerPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
