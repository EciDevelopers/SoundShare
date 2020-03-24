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

    //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/users -d "{"""id""":77,"""nombre""":"""LuisAlejandroJ""","""contraseña""":"""arsw123""","""nickname""":"""luisJ"""}"
    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> AddNewUser(@Valid @RequestBody Usuario newUser){
        services.saveUsuario(newUser.getId(),newUser.getNombre(),newUser.getContraseña(),newUser.getNickname());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
