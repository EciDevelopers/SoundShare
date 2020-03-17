/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "sala")
public class Sala implements Serializable {
    @Id
    private int id;
    private String nombre;
    private String genero;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="salausers",
    joinColumns=@JoinColumn(name="nombre_sala",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="nombre_usuario",referencedColumnName="nombre")
    )
    private Set<Usuario> usuarios;

    public Sala(){
        super();
    }
    public Sala(int id, String nombre, String genero,Set<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.usuarios=usuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

   
    
    
}
