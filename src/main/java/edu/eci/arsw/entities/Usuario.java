/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.entities;

import java.util.ArrayList;

/**
 *
 * @author fernando.barrera
 */
public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private ArrayList<Usuario> amigos;
    private ArrayList<Sala> salas;
    public Usuario(){
        super();
    }
    
    public Usuario(int id,String nombre,String password,ArrayList<Usuario> amigos,ArrayList<Sala> salas){
        this.nombre=nombre;
        this.password=password;
        this.amigos=amigos;
        this.salas=salas;
        this.id=id;
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
