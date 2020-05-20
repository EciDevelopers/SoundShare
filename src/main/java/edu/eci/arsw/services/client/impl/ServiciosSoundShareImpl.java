/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client.impl;

import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Grupo;
import edu.eci.arsw.entities.Rol;
import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.CancionRepository;
import edu.eci.arsw.persistence.GrupoRepository;
import edu.eci.arsw.persistence.RolRepository;
import edu.eci.arsw.persistence.SalaRepository;
import edu.eci.arsw.persistence.UsuarioRepository;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private GrupoRepository grupoRepository;
     
    public List<Usuario> getAllUsers() throws ExceptionServiciosReserva {
        return usuarioRepository.findAll();
    }
    public List<Sala> getAllSalas() throws ExceptionServiciosReserva {
        return salaRepository.findAll();
    }
    public List<Cancion> getAllCanciones() throws ExceptionServiciosReserva {
        return cancionRepository.findAll();
    }
    public List<Grupo> getAllGrupos() throws ExceptionServiciosReserva {
        return grupoRepository.findAll();
    }
    public Grupo getGrupoById(int id) throws ExceptionServiciosReserva {
        Grupo grupo = null;
        try{
            grupo = grupoRepository.findById(id);
            if(grupo==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return grupo;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return grupo;
    }
    public Rol getRolById(int id){
        Rol rol = null;
        try{
            rol = rolRepository.findById(id);
            if(rol==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return rol;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return rol;
    }
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
     public Cancion getCancionById(String id) throws ExceptionServiciosReserva{
        Cancion cancion = null;
        try{
            cancion = cancionRepository.findById(id);
            if(cancion==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return cancion;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return cancion;

    }
     public Cancion getCancionByName(String name) throws ExceptionServiciosReserva{
         Cancion cancion = null;
         try{
             cancion = cancionRepository.findByNombre(name);
             if(cancion==null){
                 throw new ExceptionServiciosReserva("nulo");
             }
             return cancion;
         }catch(Exception e){
         	e.printStackTrace();
         }
 		return cancion;

     }
     public Sala getSalaById(int id)throws ExceptionServiciosReserva{
         Sala sala = null;
         try{
             sala = salaRepository.findById(id);
             if(sala==null){
                 throw new ExceptionServiciosReserva("nulo");
             }
             return sala;
         }catch(Exception e){
         	e.printStackTrace();
         }
 		return sala;

     }
     public Sala getSalaByName(String nombre) throws ExceptionServiciosReserva{
        Sala sala = null;
        try{
            sala = salaRepository.findByNombre(nombre);
            if(sala==null){
                throw new ExceptionServiciosReserva("nulo");
            }
            return sala;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return sala;

    }
     public Usuario getUsuarioByNinckname(String nick)  throws ExceptionServiciosReserva{
         Usuario usuario = null;
         try{
             usuario = usuarioRepository.findByNickname(nick);
             if(usuario==null){
                 throw new ExceptionServiciosReserva("nulo");
             }
             return usuario;
         }catch(Exception e){
         	e.printStackTrace();
         }
 		return usuario;

     }


     public void saveUsuario(Usuario usr) throws ExceptionServiciosReserva{
         Set<Rol> rol=new HashSet<Rol>();
         Rol role=getRolById(2);
         rol.add(role);
         usr.setRol(rol);
         usuarioRepository.save(usr);

                 
         
     }
    public Set<Sala> getSalasByUser(String nick) throws ExceptionServiciosReserva{
        Usuario usr=getUsuarioByNinckname(nick);
        return usr.getSalas();
    }
    public Set<Cancion> getCancionesByUser(String nick) throws ExceptionServiciosReserva{
         Usuario usr=getUsuarioByNinckname(nick);
         return usr.getCanciones();
    }
    public Set<Rol> getRolesOfUser(String nick) throws ExceptionServiciosReserva{
         Usuario usr=getUsuarioByNinckname(nick);
         return usr.getRol();
    }     

    public void saveSala(Sala sala) throws ExceptionServiciosReserva{
        salaRepository.save(sala);
    }
    public void saveGrupo(Grupo grupo) throws ExceptionServiciosReserva{
        grupoRepository.save(grupo);
    }
    public Set<Usuario> getUsersBySala(String name)  throws ExceptionServiciosReserva{
        Sala sala=getSalaByName(name);
        return sala.getUsuarios();
    }
    public Set<Cancion> getColaCancionesBySala(String name)  throws ExceptionServiciosReserva{
        Sala sala=getSalaByName(name);
        return sala.getColacanciones();
    }
    public void saveCancion(Cancion can) throws ExceptionServiciosReserva{
        can.setMinuto(LocalTime.of(00, 00, 01));
        cancionRepository.save(can);
    }
    public Set<Usuario> getUsersThatHaveCancion(String id)  throws ExceptionServiciosReserva{
        Cancion cancion=getCancionById(id);
        return cancion.getUsuarios();
    }
    public Set<Sala> getSalasThatHaveCancion(String id)  throws ExceptionServiciosReserva{
        Cancion cancion=getCancionById(id);
        return cancion.getSalas();
    }
    public void addUserToSala(String nick,int SalaId) throws ExceptionServiciosReserva{
        System.out.println(nick);
        System.out.println(SalaId);
        Sala salaNow=salaRepository.findById(SalaId);
        Set<Usuario> users=salaNow.getUsuarios();
        Usuario user=usuarioRepository.findByNickname(nick);
        users.add(user);
        salaNow.setUsuarios(users);
    }
    public void exitUserToSala(String nick,int SalaId) throws ExceptionServiciosReserva{
        System.out.println(nick);
        System.out.println(SalaId);
        Sala salaNow=salaRepository.findById(SalaId);
        Set<Usuario> users=salaNow.getUsuarios();
        Usuario user=usuarioRepository.findByNickname(nick);
        users.remove(user);
        salaNow.setUsuarios(users);
    }
    public Set<Usuario> getUserBySala(int SalaId) throws ExceptionServiciosReserva{
        System.out.println(SalaId);
        Sala salaNow=salaRepository.findById(SalaId);
        return salaNow.getUsuarios();
       
    }
    public void agregarCancionToSala(int SalaId,String cancion) throws ExceptionServiciosReserva{
        System.out.println(cancion);
        Sala salaNow=salaRepository.findById(SalaId);
        Set<Cancion> lista = salaNow.getColacanciones();
        Cancion song = cancionRepository.findByNombre(cancion);
        System.out.println(song.getNombre());
        lista.add(song);
        salaNow.setColacanciones(lista);
       
    }
    public Set<Cancion> getCancionesBySala(int SalaId) throws ExceptionServiciosReserva{
        System.out.println(SalaId);
        Sala salaNow=salaRepository.findById(SalaId);
        return salaNow.getColacanciones();
       
    }

    
}
