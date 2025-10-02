package com.gestor_de_proyectos.entity;

import com.gestor_de_proyectos.entity.enums.ProgramaProjecto;
import com.gestor_de_proyectos.entity.enums.Estado;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 255, nullable = false)
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "estudiante_proyecto",
            joinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    )
    private List<Estudiante> estudiantes;

    @Column(unique = true, length = 450, nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgramaProjecto areaProjecto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @Column(nullable = false)
    private  LocalDateTime fechaActualizacion = LocalDateTime.now();

    //Contructor vacio
    public Proyecto() {

    }

    //Constructor con parametros

    public Proyecto(String nombre, List<Estudiante> estudiantes, String descripcion, ProgramaProjecto areaProjecto, Estado estado, Double valor, LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaActualizacion) {
        this.nombre = nombre;
        this.estudiantes = estudiantes;
        this.descripcion = descripcion;
        this.areaProjecto = areaProjecto;
        this.estado = estado;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaActualizacion = fechaActualizacion;
    }


    //Getters and Setters


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
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

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
