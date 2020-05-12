/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services.client.impl;

public class ExceptionServiciosReserva extends Exception {
    
    public ExceptionServiciosReserva(){
        super();
    }
    
    public ExceptionServiciosReserva(String message){
        super(message);
    }
    
    public ExceptionServiciosReserva(String string, Throwable cause) {
        super(string, cause);
    }
}
