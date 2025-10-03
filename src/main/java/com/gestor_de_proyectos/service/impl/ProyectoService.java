package com.gestor_de_proyectos.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gestor_de_proyectos.dto.EstudianteDTO;
import com.gestor_de_proyectos.dto.ProyectoDTO;
import com.gestor_de_proyectos.entity.Estudiante;
import com.gestor_de_proyectos.entity.Proyecto;
import com.gestor_de_proyectos.repository.EstudianteRepo;
import com.gestor_de_proyectos.repository.ProyectoRepo;
import com.gestor_de_proyectos.service.IProyectoService;

import jakarta.transaction.Transactional;

@Service
public class ProyectoService extends CRUDImpl<Proyecto, Long> implements IProyectoService {

    @Autowired
    private ProyectoRepo repo;
    
    @Autowired
    private EstudianteRepo estudianteRepository;
    
    @Autowired
	private ModelMapper mapper;

    @Override
    protected JpaRepository<Proyecto, Long> getRepo() {
        return repo;
    }
    
    @Transactional
    public Proyecto crear(ProyectoDTO dto) {
        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        // Crear el proyecto
        Proyecto proyecto = mapper.map(dto, Proyecto.class);
        proyecto.setFechaActualizacion(LocalDateTime.now());

        // Procesar estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();
        
        if (dto.getEstudiantes() != null) {
            for (EstudianteDTO estudianteDTO : dto.getEstudiantes()) {
                Estudiante estudiante;
                
                if (estudianteDTO.getId() != null) {
                    estudiante = estudianteRepository.findById(estudianteDTO.getId())
                            .orElseThrow(() -> new RuntimeException(
                                "Estudiante con ID " + estudianteDTO.getId() + " no encontrado"
                            ));
                } else if (estudianteDTO.getNombres() != null && !estudianteDTO.getNombres().isBlank()) {
                    estudiante = mapper.map(estudianteDTO, Estudiante.class);
                    estudiante = estudianteRepository.save(estudiante);
                    
                } else {
                    throw new RuntimeException("El estudiante debe tener un ID o un nombre válido");
                }
                
                estudiantes.add(estudiante);
            }
        }

        proyecto.setEstudiantes(estudiantes);

        return repo.save(proyecto);
    }

}
