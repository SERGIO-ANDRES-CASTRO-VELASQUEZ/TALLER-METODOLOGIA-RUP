package com.gestortareas.gestor_de_proyector.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String Nombre;

    //@Column(length = 100, nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "estudiante_proyecto",
            joinColumns = @JoinColumn (name = "estudiante_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn ( name = "proyecto_id", referencedColumnName = "id")

    )
    private List<Proyecto> proyectos;

    //Contructor vacio
    public Estudiante() {
    }

    //Constructor con parametros

    public Estudiante(String nombre, List<Proyecto> proyectos) {
        Nombre = nombre;
        this.proyectos = proyectos;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

}