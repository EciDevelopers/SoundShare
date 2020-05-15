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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;

@Controller
@Transactional
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate mgt;

    @Autowired
    ServiciosSoundShareImpl services;
    
    private ConcurrentHashMap<Integer,ArrayList<String>> mapa = new ConcurrentHashMap<Integer,ArrayList<String>>();


    @MessageMapping("/sala/{id}/unir/{nick}")
    @SendTo("/topic/sala/{id}")
    public Set<Usuario> addUsersToSala(@DestinationVariable String nick,@DestinationVariable int id){
        System.out.println("llego xd");
        try {
        	if(!(mapa.containsKey(id))  || (services.getUserBySala(id)).size() == 0) {
        		ArrayList<String> lista = new ArrayList<String>();
        		lista.add(" ");
        		lista.add(" ");
        		mapa.put(id,lista);
        	}
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
			System.out.println((services.getUserBySala(id)).size());
			if((services.getUserBySala(id)).size() == 0) {
				System.out.println("firestone");
        		mapa.remove(id);
        	}
			return services.getUserBySala(id);
		} catch (ExceptionServiciosReserva e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @MessageMapping("/sala/{id}/play/{user}/estado/{estado}/intento/{cont}")
    @SendTo("/topic/sala/{id}/play")
    public ArrayList<String> getEstado(@DestinationVariable int id,@DestinationVariable String user,@DestinationVariable boolean estado,@DestinationVariable int cont){
        System.out.println("llego estado xd");
        try {
        	ArrayList<String> tempo = new ArrayList<String>();
        	tempo.add(user);
        	tempo.add(String.valueOf(estado));
        	tempo.add(String.valueOf(cont++));
			return tempo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    
    @MessageMapping("/sala/{id}/cancionActual/{nombre}/seg/{time}")
    @SendTo("/topic/sala/{id}/cancionActual")
    public ArrayList<String> getCancionActual(@DestinationVariable String nombre,@DestinationVariable int id,@DestinationVariable int time){
        System.out.println("llego hope xd");
        try {   
        	if(nombre.equals(" ")) {
        		ArrayList<String> tempo = new ArrayList<String>();
        		tempo.add("ini");
        		tempo.add("ini");
    			return tempo;
        		
        	}
        	else if(((mapa.get(id)).get(0)).equals(" ")) {
        		System.out.println("no se xd");
        		services.agregarCancionToSala(id, nombre);
        		ArrayList<String> tempo = new ArrayList<String>();
        		Cancion can = services.getCancionById(nombre);
        		String nameExploit= can.getNombre();
        		tempo.add( nameExploit);
        		tempo.add("0");
        		mapa.put(id,tempo);
    			return mapa.get(id);
        	}	
        	else {
        		System.out.println("no se 15  xd");
        		if(Integer.parseInt((mapa.get(id)).get(1)) < time) {
        			System.out.println("update");
        			ArrayList<String> tempo = new ArrayList<String>();
        			String tempo2 = (mapa.get(id)).get(0);
        			String tempo3 = String.valueOf(time);
        			tempo.add(tempo2);
        			tempo.add(tempo3);
        			mapa.put(id,tempo);
        		}
        		return mapa.get(id);
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }



}
