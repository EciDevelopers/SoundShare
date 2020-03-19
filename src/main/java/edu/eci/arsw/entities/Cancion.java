/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.entities;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="colacanciones",
    joinColumns=@JoinColumn(name="id_cancion",referencedColumnName ="id"),
    inverseJoinColumns=@JoinColumn(name="nombre_sala",referencedColumnName="nombre")
    )
    private Set<Sala> salas;


    public Cancion(String id, String nombre, String genero, String author,Set<Sala> salas) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.author = author;
        this.salas=salas;
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

   
    
    
}
