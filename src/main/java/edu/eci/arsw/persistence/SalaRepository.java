/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import java.util.List;

/**
 *
 * @author jm_14
 */
@Repository
@Component
public interface SalaRepository extends JpaRepository< Sala, Long > {
    public  List<Sala> findAll();
    public Sala findByNombre(String nombre);
    public Sala findById(int id);
    
    
}
