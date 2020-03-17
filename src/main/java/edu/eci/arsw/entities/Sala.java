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
public class Sala {
    private int id;
    private String nombre;
    private String genero;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cancion> canciones;

    public Sala(int id, String nombre, String genero, ArrayList<Usuario> usuarios, ArrayList<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.usuarios = usuarios;
        this.canciones = canciones;
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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
    
    
}
