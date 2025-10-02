package com.gestortareas.gestor_de_proyector.controller;

import com.gestortareas.gestor_de_proyector.entity.Proyecto;
import com.gestortareas.gestor_de_proyector.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Proyecto> proyectos = proyectoService.obtenerTodos();
            return new ResponseEntity<>(proyectos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar-proyecto/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Long id) {
        try {
            Proyecto proyecto = proyectoService.obtenerPorId(id);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(proyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        try {
            Proyecto nuevoProyecto = proyectoService.crear(proyecto);
            return new ResponseEntity<>(nuevoProyecto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar-proyecto/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") Long id, @RequestBody Proyecto proyecto) {
        try {
            Proyecto proyectoExistente = proyectoService.obtenerPorId(id);
            if (proyectoExistente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            proyecto.setNombre(proyecto.getNombre());
            proyecto.setDescripcion(proyecto.getDescripcion());
            Proyecto proyectoActualizado = proyectoService.actualizar(proyecto);
            return new ResponseEntity<>(proyectoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar-proyecto/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        try {
            Proyecto proyecto = proyectoService.obtenerPorId(id);
            if (proyecto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            proyectoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
