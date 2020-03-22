/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;
import edu.eci.arsw.entities.Cancion;

import edu.eci.arsw.entities.Sala;
import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.SalaRepository;
import edu.eci.arsw.persistence.UsuarioRepository;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;

import java.util.HashSet;
import java.util.Set;
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


    @RequestMapping("/")
    public String login(){
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

    public static void main(String[] args) {
        SpringApplication.run(ApiSoundsShare.class, args);
    }
    @Bean
    public CommandLineRunner demo(ServiciosSoundShareImpl service) {
      return (args) -> {
        // save a few customers
    	Set<Sala> salas=new HashSet<Sala>();
        Set<Cancion> plays=new HashSet<Cancion>();
        Set<Sala> sal=new HashSet<Sala>();
        Cancion can=new Cancion("alfa","firestone","electronica","kygo",sal);
        Cancion can2=new Cancion("beta","playhard","electronica","davidgueta",sal);
        service.saveCancion("gama","calma","urbano","pedro capo",sal);
        plays.add(can);
        plays.add(can2);
        service.saveUsuario(15,"fernando barrera","arsw1","fer15",salas,plays);
        Usuario usr=service.getUsuarioByName("fernando barrera");
        Set<Usuario> usuarios=new HashSet<Usuario>();
        Set<Cancion> canciones=new HashSet<Cancion>();
    	usuarios.add(usr);
        canciones.add(can);
        service.saveSala(35,"hope15","electronica","publica",usuarios,canciones);
        };   
    }
   
  
        

}
