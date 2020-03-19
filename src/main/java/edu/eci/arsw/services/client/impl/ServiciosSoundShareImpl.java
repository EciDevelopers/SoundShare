/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client.impl;

import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.CancionRepository;
import edu.eci.arsw.persistence.SalaRepository;
import edu.eci.arsw.persistence.UsuarioRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author jm_14
 */
@Service
public class ServiciosSoundShareImpl {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private CancionRepository cancionRepository;
    
     public Usuario getUsuarioByName(String nombre){
        Usuario usuario = null;
        try{
            usuario = usuarioRepository.findByNombre(nombre);
            if(usuario==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return usuario;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return usuario;

    }
     public void saveUsuario(int id,String nombre,String password,String nickname,Set<Sala> salas,Set<Cancion> canciones){
         Usuario usuario=new Usuario(nombre,password,nickname,salas,canciones);
         usuarioRepository.save(usuario);
     }
    public void saveSala(int id, String nombre, String genero,String tipo,Set<Usuario> usuarios,Set<Cancion> canciones){
        Sala sala=new Sala(id,nombre,genero,tipo,usuarios,canciones);
        salaRepository.save(sala);
    }
    public void saveCancion(String id, String nombre, String genero,String author,Set<Sala> salas){
        Cancion cancion=new Cancion(id,nombre,genero,author,salas);
        cancionRepository.save(cancion);
    }


    
}
