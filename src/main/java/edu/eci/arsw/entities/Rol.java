/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
 * @author jm_14
 */
@Entity
@Table(name = "rol")
public class Rol implements Serializable{
    @Id
    private int id;
    private String valor;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="rolusuarios",
    joinColumns=@JoinColumn(name="rol_id",referencedColumnName ="id"),
    inverseJoinColumns=@JoinColumn(name="nombre_user",referencedColumnName="nombre")
    )
    private Set<Usuario> users;
    
    public Rol(){
        super();
    }

    public Rol(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Set<Usuario> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuario> users) {
        this.users = users;
    }
    
    
}
