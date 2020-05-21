/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.controllers;

import edu.eci.arsw.entities.Grupo;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos")
public class GruposController {
    @Autowired
    private ServiciosSoundShareImpl services;
    //@RequestMapping (value= "/getAll",method = RequestMethod.GET )
    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllGrupos(){
        try{
            List<Grupo> grupos = services.getAllGrupos();
            return new ResponseEntity<>(grupos, HttpStatus.ACCEPTED);
        }catch (ExceptionServiciosReserva e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(path ="/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getGrupoById(@PathVariable ("id") int id){
        try {
            return new ResponseEntity<>(services.getGrupoById(id),HttpStatus.ACCEPTED);
        } catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	}       
    }
     //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/grupos/create -d "{"""nombre""":"""amigosfer"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewGrupo(@Valid @RequestBody Grupo newGrupo){
    	System.out.println("alfa"+" "+newGrupo.getNombre());
        try {
			services.saveGrupo(newGrupo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
       
    }
    
}
