/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.services;

/**
 *
 * @author fernando.barrera
 */
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Named
@Stateless
@SessionScoped
@ManagedBean(name="InicioBean",eager =true)
public class App implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(App.class);
	private String nick;
	private String password;
	
	public App(){
	}
	public String getNick(){
		return nick;
	}
	public String getPassword(){
		return password;
	}
	public void setNick(String nick){
		this.nick = nick;
	}
	public void setPassword(String password){
		this.password = password;
	}	
	
	public void iniciar() throws IOException{
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken(nick,password);
            
            try {
                subject.login(token);
                if (subject.hasRole("admin")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("vistaadmin.xhtml");
                }
                else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect("vistauser.xhtml");
                }
                
            }
          catch (UnknownAccountException ex) {
             
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fallo de autenticacion."));
           }
            catch (IncorrectCredentialsException ex) {
                
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fallo de autenticacion."));
            }
        }        
}
