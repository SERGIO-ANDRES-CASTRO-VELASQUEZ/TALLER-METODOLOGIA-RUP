package com.gestor_de_proyectos.dto;

import com.gestor_de_proyectos.validation.ValidarEstudiante;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@ValidarEstudiante
public class EstudianteDTO {
	
	@Positive(message = "El ID debe ser un número positivo")
	private Long id;
	@Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
    private String nombres;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
    
}
