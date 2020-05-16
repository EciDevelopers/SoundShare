package edu.eci.arsw.controllers;


import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    ConcurrentHashMap<String, Cancion> canciones = new ConcurrentHashMap<String, Cancion>();
    ConcurrentHashMap<String, Sala> salas = new ConcurrentHashMap<String, Sala>();
    ConcurrentHashMap<String, Usuario> users = new ConcurrentHashMap<String, Usuario>();
    @Autowired
    ServiciosSoundShareImpl servicios;

    public Cache() throws ExceptionServiciosReserva {
        List<Cancion> songs = servicios.getAllCanciones();
        for(Cancion cancion:songs){
            canciones.put(cancion.getId(),cancion);
        }

    }

    public ConcurrentHashMap<String, Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ConcurrentHashMap<String, Cancion> canciones) {
        this.canciones = canciones;
    }

    public ConcurrentHashMap<String, Sala> getSalas() {
        return salas;
    }

    public void setSalas(ConcurrentHashMap<String, Sala> salas) {
        this.salas = salas;
    }

    public ConcurrentHashMap<String, Usuario> getUsers() {
        return users;
    }

    public void setUsers(ConcurrentHashMap<String, Usuario> users) {
        this.users = users;
    }
}
