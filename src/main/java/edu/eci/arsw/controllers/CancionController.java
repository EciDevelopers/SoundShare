/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.controllers;
import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Sala;
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
@RequestMapping(value = "/canciones")
public class CancionController {
    @Autowired
    private ServiciosSoundShareImpl services;
    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllCanciones(){
        try{
            List<Cancion> canciones = services.getAllCanciones();
            return new ResponseEntity<>(canciones, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
     //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/canciones/create -d "{"""id""":"""omega""","""nombre""":"""dance_monkey""","""genero""":"""electronica""","""author""":"""tones_and_i"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewCancion(@Valid @RequestBody Cancion newCancion){
    	System.out.println("alfa"+" "+newCancion.getNombre());
        try {
			services.saveCancion(newCancion);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
       
    }
    @RequestMapping(path ="/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCancionById(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(services.getCancionById(id),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
    @RequestMapping(path ="/{id}/users",method = RequestMethod.GET)
    public ResponseEntity<?> getUsersThatHaveCancion(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(services.getUsersThatHaveCancion(id),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
     @RequestMapping(path ="/{id}/salas",method = RequestMethod.GET)
    public ResponseEntity<?>getSalasThatHaveCancion(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(services.getSalasThatHaveCancion(id),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }

}
