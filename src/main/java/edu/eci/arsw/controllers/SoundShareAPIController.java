package edu.eci.arsw.controllers;

import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class SoundShareAPIController {
    @Autowired
    private ServiciosSoundShareImpl services;


    @RequestMapping (method = RequestMethod . GET )
    public ResponseEntity<?>  getAllUsers(){
        try{
            List<Usuario> usuarios = services.getAllUsers();
            return new ResponseEntity<>(usuarios, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
