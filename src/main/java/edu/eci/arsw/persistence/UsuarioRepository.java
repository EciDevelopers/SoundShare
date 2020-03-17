/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.persistence;

import edu.eci.arsw.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



/**
 * AirlineRepository
 */
@Repository
@Component
public interface UsuarioRepository extends JpaRepository< Usuario, Long >{

    public Usuario findByNombre(String nombre);
    public Usuario findByNickname(String nick);

    
}