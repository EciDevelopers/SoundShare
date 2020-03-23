package edu.eci.arsw.controllers;

import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class SoundShareAPIController {

    @Autowired
    private ServiciosSoundShareImpl services;


    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllUsers(){
        try{
            List<Usuario> usuarios = services.getAllUsers();
            return new ResponseEntity<>(usuarios, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    /**
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> AddNewUser(@RequestBody Usuario newUser){
        try {
            services.saveUsuario(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(SoundShareAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }**/

}
