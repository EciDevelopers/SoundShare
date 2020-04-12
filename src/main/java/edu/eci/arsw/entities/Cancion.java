/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author fernando.barrera
 */
@Entity
@Table(name = "cancion")
public class Cancion {
    @Id
    private String id;
    private String nombre;
    private String genero;
    private String author;
    private LocalTime minuto;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="colacanciones",
    joinColumns=@JoinColumn(name="id_cancion",referencedColumnName ="id"),
    inverseJoinColumns=@JoinColumn(name="nombre_sala",referencedColumnName="nombre")
    )
    private Set<Sala> salas;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="usercanciones",
    joinColumns=@JoinColumn(name="id_cancion",referencedColumnName ="id"),
    inverseJoinColumns=@JoinColumn(name="nombre_user",referencedColumnName="nombre")
    )
    private Set<Usuario> usuarios;


    public Cancion(String id, String nombre, String genero, String author) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.author = author;
        this.salas=new HashSet<Sala>();
        this.usuarios=new HashSet<Usuario>();
        
    }
    public Cancion() {
    	 super();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }

    public LocalTime getMinuto() {
        return minuto;
    }

    public void setMinuto(LocalTime minuto) {
        this.minuto = minuto;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
   
    
    
}
