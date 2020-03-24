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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author fernando.barrera
 */

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String contraseña;
    private String nickname;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="salausers",
    joinColumns=@JoinColumn(name="nombre_usuario",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="nombre_sala",referencedColumnName="nombre")
    )
    private Set<Sala> salas;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="usercanciones",
    joinColumns=@JoinColumn(name="nombre_user",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="id_cancion",referencedColumnName="id")
    )
    private Set<Cancion> canciones;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="rolusuarios",
    joinColumns=@JoinColumn(name="nombre_user",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="rol_id",referencedColumnName="id")
    )
    private Set<Rol> rol;
   
    
  
    

    public Usuario(){
        super();
    }
    
    public Usuario(String nombre,String contraseña,String nickname){
        this.nombre=nombre;
        this.contraseña=contraseña;
        this.nickname=nickname;
        //this.salas=salas;
        //this.canciones=canciones;
   
       
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Set<Rol> getRol() {
        return rol;
    }

    public void setRol(Set<Rol> rol) {
        this.rol = rol;
    }
    
    

    
    
    
    
}
