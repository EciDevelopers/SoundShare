package edu.eci.arsw.controllers;

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


    //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/users/create -d "{"""id""":3,"""nombre""":"""carlos""","""pass""":"""arsw2""","""nickname""":"""carlos2"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewUser(@Valid @RequestBody Usuario newUser){
    	System.out.println("alfa"+" "+newUser.getNombre());
        try {
			services.saveUsuario(newUser);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
       
    }


}
