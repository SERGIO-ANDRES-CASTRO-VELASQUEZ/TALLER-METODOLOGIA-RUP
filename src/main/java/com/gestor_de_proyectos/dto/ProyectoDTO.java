package com.gestor_de_proyectos.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gestor_de_proyectos.entity.enums.Estado;
import com.gestor_de_proyectos.entity.enums.ProgramaProjecto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class ProyectoDTO {
	
	private Long id;
	
	@NotBlank(message = "El nombre del proyecto es obligatorio")
	@Size(min = 5, max = 255, message = "El nombre debe tener entre 5 y 255 caracteres")
	private String nombre;
	
	@NotBlank(message = "La descripción es obligatoria")
	@Size(min = 10, max = 450, message = "La descripción debe tener entre 10 y 450 caracteres")
    private String descripcion;
	
	@NotNull(message = "El área del proyecto es obligatoria")
    private ProgramaProjecto areaProjecto;
	
	@NotNull(message = "El estado es obligatorio")
    private Estado estado;
	
	@NotNull(message = "El valor es obligatorio")
	@Positive(message = "El valor debe ser mayor a cero")
	@DecimalMin(value = "0.01", message = "El valor mínimo es 0.01")
    private Double valor;
	
	@NotNull(message = "La fecha de inicio es obligatoria")
	@PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDateTime fechaInicio;
	
	@NotNull(message = "La fecha de fin es obligatoria")
	@Future(message = "La fecha de fin debe ser futura")
    private LocalDateTime fechaFin;
	
	@NotEmpty(message = "Debe haber al menos un estudiante asignado")
	@Valid
    private List<EstudianteDTO> estudiantes;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ProgramaProjecto getAreaProjecto() {
		return areaProjecto;
	}
	public void setAreaProjecto(ProgramaProjecto areaProjecto) {
		this.areaProjecto = areaProjecto;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public List<EstudianteDTO> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(List<EstudianteDTO> estudiantes) {
		this.estudiantes = estudiantes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}