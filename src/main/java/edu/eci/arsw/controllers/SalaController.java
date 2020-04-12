/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.controllers;
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
@RequestMapping(value = "/salas")
public class SalaController {
    @Autowired
    private ServiciosSoundShareImpl services;
    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllSalas(){
        try{
            List<Sala> salas = services.getAllSalas();
            return new ResponseEntity<>(salas, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
     //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/salas/create -d "{"""id""":3,"""nombre""":"""electromix""","""genero""":"""electronica""","""tipo""":"""privada"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewSala(@Valid @RequestBody Sala newSala){
    	System.out.println("alfa"+" "+newSala.getNombre());
        try {
			services.saveSala(newSala);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
       
    }
    @RequestMapping(path ="/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> getSalaByNombre(@PathVariable ("name") String name){
        try {
            return new ResponseEntity<>(services.getSalaByName(name),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
     @RequestMapping(path ="/{name}/users",method = RequestMethod.GET)
    public ResponseEntity<?> getUsersBySala(@PathVariable ("name") String name){
        try {
            return new ResponseEntity<>(services.getUsersBySala(name),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
     @RequestMapping(path ="/{name}/colacanciones",method = RequestMethod.GET)
    public ResponseEntity<?> getColaCancionesBySala(@PathVariable ("name") String name){
        try {
            return new ResponseEntity<>(services.getColaCancionesBySala(name),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
}
