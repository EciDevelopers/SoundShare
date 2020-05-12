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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate mgt;

    @Autowired
    ServiciosSoundShareImpl services;


    @MessageMapping("/sala/{id}/unir/{nick}")
    @SendTo("/topic/sala/{id}")
    public Set<Usuario> addUsersToSala(@DestinationVariable String nick,@DestinationVariable int id){
        System.out.println("llego xd");
        try {
			services.addUserToSala(nick,id);
			return services.getUserBySala(id);
		} catch (ExceptionServiciosReserva e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @MessageMapping("/sala/{id}/salir/{nick}")
    @SendTo("/topic/sala/{id}")
    public Set<Usuario> exitUsersToSala(@DestinationVariable String nick,@DestinationVariable int id){
        System.out.println("llego 15 xd");
        try {
			services.exitUserToSala(nick,id);
			return services.getUserBySala(id);
		} catch (ExceptionServiciosReserva e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }



}
