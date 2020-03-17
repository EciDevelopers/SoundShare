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
    private String rol;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="salausers",
    joinColumns=@JoinColumn(name="nombre_usuario",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="nombre_sala",referencedColumnName="nombre")
    )
    private Set<Sala> salas;
    

    public Usuario(){
        super();
    }
    
    public Usuario(String nombre,String contraseña,String nickname,Set<Sala> salas){
        this.nombre=nombre;
        this.contraseña=contraseña;
        this.nickname=nickname;
        this.rol="user";
        this.salas=salas;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
