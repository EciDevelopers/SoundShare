/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.persistence;

import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Rol;
import edu.eci.arsw.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jm_14
 */
public interface RolRepository extends JpaRepository< Rol, Long >{
     public Rol findById(int id);
     
    
}
