/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;
import edu.eci.arsw.entities.Cancion;
import edu.eci.arsw.entities.Rol;

import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.SalaRepository;
import edu.eci.arsw.persistence.UsuarioRepository;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;


import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;

import java.util.HashSet;
import java.util.Set;


import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author fernando.barrera
 */
@Controller
@SpringBootApplication
@ComponentScan("edu.eci.arsw")
@EntityScan("edu.eci.arsw.entities")
@EnableJpaRepositories("edu.eci.arsw.persistence")
public class ApiSoundsShare {

	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 public String login(Model model,String error, String logout) {
		
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "index";
	 }



    @RequestMapping("/img/")
    public String images(){
        return "/img";
    }

    @RequestMapping("/css/")
    public String css(){
        return "/css";
    }

    @RequestMapping("/js/")
    public String js(){
        return "/js";
    }

    @RequestMapping("/fonts/")
    public String fonts(){
        return "/fonts";
    }

    @RequestMapping("/vendor/")
    public String vendor(){
        return "/vendor";
    }
    @RequestMapping("/html/")
    public String html(){
    	return "/html"; 
    }

    @RequestMapping("/index.html")
    public String logout(){
        return "index";
    }

    @RequestMapping("/html/sala.html")
    public String sala(){
        return "html/sala";
    }

    @RequestMapping("/html/adminScreen.html")
    public String adminScreen(){
        return "html/adminScreen";
    }


    @RequestMapping("html/userScreen.html")
    public String userScreen(){
        return "html/userScreen";
    }

    @RequestMapping("html/singUp.html")
    public String singUp(){
        return "html/singUp";
    }

    @RequestMapping("html/prueba.html")
    public String prueba(){
        return "html/prueba";
    }

    @RequestMapping("html/addSong.html")
    public String addSong(){
        return "html/addSong";
    }
	
	@RequestMapping("html/google5a82269014c7c9b2.html")
    public String google5a82269014c7c9b2(){
        return "html/google5a82269014c7c9b2";
    }
	


    public static void main(String[] args) {
	     SpringApplication.run(ApiSoundsShare.class, args);
    }

    /*
    @Bean
    public CommandLineRunner demo(ServiciosSoundShareImpl service) {
      return (args) -> {
        // save a few customers
    	
    	Set<Sala> salas=new HashSet<Sala>();
        Set<Cancion> plays=new HashSet<Cancion>();
        Set<Sala> sal=new HashSet<Sala>();
        Set<Rol> rol=new HashSet<Rol>();
        Rol role=service.getRolById(1);
        rol.add(role);
        Cancion can=new Cancion("alfa","firestone","electronica","kygo");
        Cancion can2=new Cancion("beta","playhard","electronica","davidgueta");
        service.saveCancion(can);
        service.saveCancion(can2);
        LocalTime time15 = LocalTime.of(00, 01, 15);
        can2.setMinuto(time15);
        plays.add(can);
        plays.add(can2);
        Usuario usr15=new Usuario(15,"fernando barrera","arsw1","fer15");
        service.saveUsuario(usr15);
        Usuario usr=service.getUsuarioByName("fernando barrera");
        usr.setRol(rol);
        usr.setCanciones(plays);
        Set<Usuario> usuarios=new HashSet<Usuario>();
        Set<Cancion> canciones=new HashSet<Cancion>();
    	usuarios.add(usr);
        canciones.add(can);
        Sala sala=new Sala(35,"hope15","electronica","publica",usuarios,canciones);
        service.saveSala(sala);
        
  
        };   
       
    }
     */
    
}
