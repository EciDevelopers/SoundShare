/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

/**
 *
 * @author fernando.barrera
 */
@Controller
@SpringBootApplication
@ComponentScan("edu.eci.arsw")
public class ApiSoundsShare {

    @RequestMapping("/")
    public String login(){
        return "index";
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

    public static void main(String[] args) {
        SpringApplication.run(ApiSoundsShare.class, args);
    }

}
