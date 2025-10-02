package com.gestortareas.gestor_de_proyector.controller;

import com.gestortareas.gestor_de_proyector.entity.Estudiante;
import com.gestortareas.gestor_de_proyector.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Estudiante> estudiantes = estudianteService.obtenerTodos();
            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Long id) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            if (estudiante == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.crear(estudiante);
            return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long id, @RequestBody Estudiante estudiante) {
        try {
            Estudiante estudianteExistente = estudianteService.obtenerPorId(id);
            if (estudianteExistente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            estudiante.setNombre(estudiante.getNombre()); // Usamos setNombre en lugar de setId
            Estudiante estudianteActualizado = estudianteService.actualizar(estudiante);
            return new ResponseEntity<>(estudianteActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            if (estudiante == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            estudianteService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

