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

import com.gestor_de_proyectos.dto.EstudianteDTO;
import com.gestor_de_proyectos.entity.Estudiante;
import com.gestor_de_proyectos.exception.ModeloNotFoundException;
import com.gestor_de_proyectos.service.IEstudianteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;
    
    @Autowired
	private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarTodos() throws Exception{
    	List<EstudianteDTO> lista = estudianteService.obtenerTodos().stream().map(e -> mapper.map(e, EstudianteDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/buscar-estudiante/{id}")
    public ResponseEntity<EstudianteDTO> listarPorId(@PathVariable("id") Long id) throws Exception {
		Estudiante obj = estudianteService.obtenerPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
						
		EstudianteDTO dto = mapper.map(obj, EstudianteDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody EstudianteDTO dto) throws Exception {
		Estudiante e = mapper.map(dto, Estudiante.class);
		Estudiante obj = estudianteService.crear(e);
		
		//localhost:8080/Estudiantes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

    @PutMapping("/actualizar-estudiante/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@Valid @RequestBody EstudianteDTO dto) throws Exception {
		Estudiante obj = estudianteService.obtenerPorId(dto.getId());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getId());
		}
		
		Estudiante e = mapper.map(dto, Estudiante.class);		
		Estudiante eActulizado = estudianteService.actualizar(e);
		EstudianteDTO dtoResponse = mapper.map(eActulizado, EstudianteDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

    @DeleteMapping("/eliminar-estudiante/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception {
		Estudiante obj = estudianteService.obtenerPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		estudianteService.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

