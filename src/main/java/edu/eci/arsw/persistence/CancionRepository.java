/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.persistence;

import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Sala;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jm_14
 */
public interface CancionRepository  extends JpaRepository< Cancion, Long >{
     public  List<Cancion> findAll();
     public Cancion findById(String id);
}
