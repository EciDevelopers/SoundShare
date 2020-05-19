/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.controllers;
import edu.eci.arsw.cache.Cache;
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
    private Cache cache;
    @Autowired
    private ServiciosSoundShareImpl services;
    //@RequestMapping (value= "/getAll",method = RequestMethod.GET )
    @RequestMapping (method = RequestMethod.GET )
    public ResponseEntity<?>  getAllCanciones(){
        try{
            if(cache.getAllCanciones().size()==0){
                //System.out.println("Prueba");
                cache.update("cancion");
            }
            List<Cancion> canciones = cache.getAllCanciones();
            return new ResponseEntity<>(canciones, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
     //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080/canciones/create -d "{"""id""":"""omega""","""nombre""":"""dance_monkey""","""genero""":"""electronica""","""author""":"""tones_and_i"""}"
    @PostMapping("/create")
    public ResponseEntity<?> AddNewCancion(@Valid @RequestBody Cancion newCancion){
    	System.out.println("alfa"+" "+newCancion.getNombre());
        try {
			services.saveCancion(newCancion);
			//update
            cache.update("cancion");
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (ExceptionServiciosReserva e) {
			
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
    }

    @RequestMapping(path ="/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCancionById(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(cache.getCancionById(id),HttpStatus.ACCEPTED);
            //meodo que dado el id recorne la cancion con esa id
        } catch (Exception e) {
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
    @RequestMapping(path ="/getByName/{nombre}",method = RequestMethod.GET)
    public ResponseEntity<?> getCancionByNombre(@PathVariable ("nombre") String nombre){
        try {
            return new ResponseEntity<>(cache.getCancionByNombre(nombre),HttpStatus.ACCEPTED);
        } catch (Exception e) {
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
    @RequestMapping(path ="/{id}/users",method = RequestMethod.GET)
    public ResponseEntity<?> getUsersThatHaveCancion(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(cache.getUsersThatHaveCancion(id),HttpStatus.ACCEPTED);
        }catch (Exception e) {
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }
    @RequestMapping(path ="/{id}/salas",method = RequestMethod.GET)
    public ResponseEntity<?>getSalasThatHaveCancion(@PathVariable ("id") String id){
        try {
            return new ResponseEntity<>(cache.getSalasThatHaveCancion(id),HttpStatus.ACCEPTED);
        } catch (Exception e) {
			Logger.getLogger(ExceptionServiciosReserva.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	    }
    }

}
