package edu.eci.arsw.cache;


import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.CancionRepository;
import edu.eci.arsw.persistence.SalaRepository;
import edu.eci.arsw.persistence.UsuarioRepository;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Cache {

    ConcurrentHashMap<String, Cancion> canciones = new ConcurrentHashMap<String, Cancion>();
    ConcurrentHashMap<String, Sala> salas = new ConcurrentHashMap<String, Sala>();
    ConcurrentHashMap<String, Usuario> usuarios = new ConcurrentHashMap<String, Usuario>();
    @Autowired
    private UsuarioRepository usuarioR;
    @Autowired
    private SalaRepository salaR;
    @Autowired
    private CancionRepository cancionR;


    /**
    public Cache(){
        List<Cancion> songs = cancionR.findAll();
        for(Cancion cancion:songs){
            canciones.put(cancion.getId(),cancion);
        }
        List<Sala> sala = salaR.findAll();
        for(Sala sal:sala){
            salas.put(sal.getNombre(),sal);
        }
        List<Usuario> users = usuarioR.findAll();
        for(Usuario usr:users){
            usuarios.put(usr.getNickname(),usr);
        }
    }*/

    public void update(String tipo) throws ExceptionServiciosReserva {
        if(tipo=="cancion"){
            List<Cancion> newSongs = cancionR.findAll();
            for(Cancion cancion:newSongs){
                canciones.put(cancion.getId(),cancion);
            }

        }else if(tipo=="sala"){
            List<Sala> newSala = salaR.findAll();
            for(Sala sal:newSala){
                salas.put(sal.getNombre(),sal);
            }

        }else if(tipo=="usuario"){
            List<Usuario> newUsers = usuarioR.findAll();
            for(Usuario usr:newUsers){
                usuarios.put(usr.getNickname(),usr);
            }

        }
    }
    public List<Cancion> getAllCanciones(){
        List<Cancion> songs = new ArrayList<>();
        for(Cancion song:canciones.values()){
            songs.add(song);
        }
        return songs;
    }

    public Cancion getCancionById(String id){
        return canciones.get(id);
    }

    public Cancion getCancionByNombre(String nombre){
        List<Cancion> lCanciones =  getAllCanciones();
        for(Cancion song:lCanciones){
            if(song.getNombre().equals(nombre)){
                return song;
            }
        }
        return null;
    }

    public Set<Usuario> getUsersThatHaveCancion(String id){
        //System.out.println(getCancionById(id).getUsuarios());
        return getCancionById(id).getUsuarios();
    }

    public Set<Sala> getSalasThatHaveCancion(String id){
        return getCancionById(id).getSalas();
    }

}
