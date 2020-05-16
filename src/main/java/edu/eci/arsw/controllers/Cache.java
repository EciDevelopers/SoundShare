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
    ConcurrentHashMap<String, Usuario> usuarios = new ConcurrentHashMap<String, Usuario>();
    @Autowired
    ServiciosSoundShareImpl servicios;

    public Cache() throws ExceptionServiciosReserva {
        List<Cancion> songs = servicios.getAllCanciones();
        for(Cancion cancion:songs){
            canciones.put(cancion.getId(),cancion);
        }
        List<Sala> sala = servicios.getAllSalas();
        for(Sala sal:sala){
            salas.put(sal.getNombre(),sal);
        }
        List<Usuario> users = servicios.getAllUsers();
        for(Usuario usr:users){
            usuarios.put(usr.getNickname(),usr);
        }
    }

}
