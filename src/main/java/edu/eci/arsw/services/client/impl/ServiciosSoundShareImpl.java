/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client.impl;

import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
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
    
     public Usuario getUsuarioByName(String nombre)  throws ExceptionServiciosReserva{
        Usuario usuario = null;
        try{
            usuario = usuarioRepository.findByNombre(nombre);
            if(usuario==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return usuario;
        }catch(Exception e){
            throw new ExceptionServiciosReserva("xd");
        }

    }
     public void saveUsuario(int id,String nombre,String password,String nickname){
         Usuario usuario=new Usuario(nombre,password,nickname);
         usuarioRepository.save(usuario);
     }
    public void saveSala(int id, String nombre, String genero,Set<String> usuarios){
        Sala sala=new Sala(id,nombre,genero,usuarios);
        salaRepository.save(sala);
    }


    
}
