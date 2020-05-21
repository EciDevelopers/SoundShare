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

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.google.gson.JsonArray;

import antlr.collections.List;

@Controller
@Transactional
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate mgt;

    @Autowired
    ServiciosSoundShareImpl services;
    
    private ConcurrentHashMap<Integer,ArrayList<Object>> mapa = new ConcurrentHashMap<Integer,ArrayList<Object>>();


    @MessageMapping("/sala/{id}/unir/{nick}")
    @SendTo("/topic/sala/{id}")
    public Set<Usuario> addUsersToSala(@DestinationVariable String nick,@DestinationVariable int id){
        System.out.println("llego xd");
        try {
        	if(!(mapa.containsKey(id))  || (services.getUserBySala(id)).size() == 0) {
        		ArrayList<Object> lista = new ArrayList<Object>();
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
				services.limpiarColaCancionesBySala(id);
        		mapa.remove(id);
        	}
			return services.getUserBySala(id);
		} catch (ExceptionServiciosReserva e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @MessageMapping("/sala/{id}/play/{users}/estado/{estado}")
    @SendTo("/topic/sala/{id}/play")
    public ArrayList<Object> getEstado(@DestinationVariable int id,@DestinationVariable String users,@DestinationVariable boolean estado){
        System.out.println("llego estado xd");
        System.out.println(users);
        JSONObject req = new JSONObject(users); 
        JSONArray array1 = req.getJSONArray("users");
        System.out.println(array1);
        ArrayList<Object> lista35 = new ArrayList<Object>();
        for(int i=0;i < array1.length();i++){
            lista35.add(array1.get(i));
        }
        try {
        	if(lista35.size() == 0) {
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		tempo.add("ini");
        		tempo.add("estado");
        		return tempo;
        	}
        	else {
        		ArrayList<Object> tempo = new ArrayList<Object>();
            	tempo.add(lista35);
            	tempo.add(String.valueOf(estado));
    			return tempo;
        		
        	}
        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    @MessageMapping("/sala/{id}/lista/{lista}/index/{pos}/seg/{time}")
    @SendTo("/topic/sala/{id}/lista")
    public ArrayList<Object> setlista(@DestinationVariable int pos,@DestinationVariable String lista,@DestinationVariable int id,@DestinationVariable int time){
        System.out.println("llego hope lista xd");
        System.out.println(lista);
        JSONObject req = new JSONObject(lista); 
        JSONArray array1 = req.getJSONArray("lista");
        System.out.println(array1);
        ArrayList<Object> lista35 = new ArrayList<Object>();
        for(int i=0;i < array1.length();i++){
            lista35.add(array1.get(i));
        }
        try {
        	if(lista35.size()==0) {
        		lista35.add("ini");
        		lista35.add("lista");
        		return lista35;
        	}
        	else {
        		System.out.println("update lista xd");
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		Cancion can = services.getCancionByName((String) lista35.get(pos));
        		String nameExploit= can.getNombre();
        		tempo.add(nameExploit);
        		tempo.add(String.valueOf(time));
        		tempo.add(lista35);
        		tempo.add(pos);
        		mapa.put(id,tempo);
        		services.setSongsToSala(id,lista35);
    			return mapa.get(id);
        		
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    @MessageMapping("/sala/{id}/next/{lista15}/index/{pos}/seg/{time}")
    @SendTo("/topic/sala/{id}/next")
    public ArrayList<Object> nextSong(@DestinationVariable int pos,@DestinationVariable String lista15,@DestinationVariable int id,@DestinationVariable int time){
    	System.out.println("llego hope next xd");
        System.out.println(lista15);
        JSONObject req = new JSONObject(lista15); 
        JSONArray array1 = req.getJSONArray("lista");
        System.out.println(array1);
        ArrayList<Object> lista = new ArrayList<Object>();
        for(int i=0;i < array1.length();i++){
            lista.add(array1.get(i));
        }
        JSONArray array2 = req.getJSONArray("users");
        System.out.println(array2);
        ArrayList<Object> lista2 = new ArrayList<Object>();
        for(int i=0;i < array2.length();i++){
            lista2.add(array2.get(i));
        }          
        try { 
        	if(lista.size() == 0) {
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		tempo.add("ini");
        		tempo.add("next");
    			return tempo;
        		
        	}
        	else {
        		System.out.println("no se next xd");
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		tempo.add(lista);
        		tempo.add(lista2);
        		tempo.add(pos);
    			return tempo;
        		
        	}
      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
    	
    }
    @MessageMapping("/sala/{id}/back/{lista15}/index/{pos}/seg/{time}")
    @SendTo("/topic/sala/{id}/back")
    public ArrayList<Object> backSong(@DestinationVariable int pos,@DestinationVariable String lista15,@DestinationVariable int id,@DestinationVariable int time){
    	System.out.println("llego hope xd backk");
        System.out.println(lista15);
        JSONObject req = new JSONObject(lista15); 
        JSONArray array1 = req.getJSONArray("lista");
        System.out.println(array1);
        ArrayList<Object> lista = new ArrayList<Object>();
        for(int i=0;i < array1.length();i++){
            lista.add(array1.get(i));
        }
        JSONArray array2 = req.getJSONArray("users");
        System.out.println(array2);
        ArrayList<Object> lista2 = new ArrayList<Object>();
        for(int i=0;i < array2.length();i++){
            lista2.add(array2.get(i));
        }
        try { 
        	if(lista.size() == 0) {
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		tempo.add("ini");
        		tempo.add("back");
    			return tempo;
        		
        	}
        	else {
        		System.out.println("no se back xd");
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		tempo.add(lista);
        		tempo.add(lista2);
        		tempo.add(pos);
    			return tempo;
        		
        	}
      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
    	
    }
    
    @MessageMapping("/sala/{id}/cancionActual/{lista15}/index/{pos}/seg/{time}")
    @SendTo("/topic/sala/{id}/cancionActual")
    public ArrayList<Object> getCancionActual(@DestinationVariable int pos,@DestinationVariable String lista15,@DestinationVariable int id,@DestinationVariable int time){
        System.out.println("llego hope xd");
        System.out.println(lista15);
        JSONObject req = new JSONObject(lista15); 
        JSONArray array1 = req.getJSONArray("lista");
        System.out.println(array1);
        ArrayList<Object> lista = new ArrayList<Object>();
        for(int i=0;i < array1.length();i++){
            lista.add(array1.get(i));
        }
        try {   
        	if(lista.size() == 0) {
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		ArrayList<Object> tempo1 = new ArrayList<Object>();
        		tempo.add("ini");
        		tempo.add("ini");
        		tempo.add(tempo1);
    			return tempo;
        		
        	}
        	else if(((mapa.get(id)).get(0)).equals(" ")) {
        		System.out.println("no se xd");
        		System.out.println(lista.get(pos));
        		services.setSongsToSala(id,lista);
        		ArrayList<Object> tempo = new ArrayList<Object>();
        		Cancion can = services.getCancionByName((String) lista.get(pos));
        		String nameExploit= can.getNombre();
        		tempo.add( nameExploit);
        		tempo.add("0");
        		tempo.add(lista);
        		tempo.add(pos);
        		mapa.put(id,tempo);
    			return mapa.get(id);
        	}	
        	else {
        		System.out.println("no se 15  xd");
        		System.out.println(time);
        		System.out.println((String)(mapa.get(id)).get(1));
        		System.out.println(Integer.parseInt((String)(mapa.get(id)).get(1)) < time);
        		String song = (String) lista.get(pos);
        		System.out.println(song);
        		if(!song.equals((String)(mapa.get(id)).get(0)) || Integer.parseInt((String)(mapa.get(id)).get(1)) < time) {
        			System.out.println("update");
        			ArrayList<Object> tempo = new ArrayList<Object>();
        			String tempo3 = String.valueOf(time);
        			tempo.add(song);
        			tempo.add(tempo3);
        			tempo.add(lista);
        			tempo.add(pos);
        			mapa.put(id,tempo);
        		}
        		/**
        		else if(!(((mapa.get(id)).get(0)).equals(nombre))) {
        			services.agregarCancionToSala(id, nombre);
        		}
        		else if(time == -1) {
        			System.out.println("change");
        			ArrayList<String> tempo = new ArrayList<String>();
        			String tempo2 = nombre;
        			String tempo3 = String.valueOf(0);
        			tempo.add(tempo2);
        			tempo.add(tempo3);
        			mapa.put(id,tempo);
        		}
        		*/
        		
        		return mapa.get(id);
        		
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
    }




}
