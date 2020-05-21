package edu.eci.arsw.controllers;

import edu.eci.arsw.cache.Cache;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;

import org.springframework.http.MediaType;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/users")
public class UsuarioController {
    @Autowired
    private Cache cache;
    @Autowired
    private ServiciosSoundShareImpl services;

    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllUsers(){
        try{
            if(cache.getAllUsuarios().size()==0){
                //System.out.println("Aca entramos");
                cache.update("usuario");
            }
            List<Usuario> usuarios = cache.getAllUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/users/create -d "{"""nombre""":"""carlos""","""pass""":"""arsw2""","""nickname""":"""carlos2"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewUser(@Valid @RequestBody Usuario newUser){
    	System.out.println("alfa"+" "+newUser.getNombre());
        try {
			services.saveUsuario(newUser);
            cache.update("usuario");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
       
    }
    
    @RequestMapping(path ="/{nick}",method = RequestMethod.GET)
    public ResponseEntity<?> getUsuarioByNick(@PathVariable ("nick") String nick){
        try {
            if(cache.getAllUsuarios().size()==0){
                System.out.println("Aca entramos");
                cache.update("usuario");
            }
            return new ResponseEntity<>(cache.getUsuarioByNick(nick),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
    @RequestMapping(path ="/{nick}/salas",method = RequestMethod.GET)
    public ResponseEntity<?> getSalasByUser(@PathVariable ("nick") String nick){
        try {
            return new ResponseEntity<>(services.getSalasByUser(nick),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
     @RequestMapping(path ="/{nick}/canciones",method = RequestMethod.GET)
    public ResponseEntity<?> getCancionesByUser(@PathVariable ("nick") String nick){
        try {
            return new ResponseEntity<>(services.getCancionesByUser(nick),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
    @RequestMapping(path ="/{nick}/roles",method = RequestMethod.GET)
    public ResponseEntity<?> getRolesOfUser(@PathVariable ("nick") String nick){
        try {
            return new ResponseEntity<>(services.getRolesOfUser(nick),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }

}
