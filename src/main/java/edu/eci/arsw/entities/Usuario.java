package edu.eci.arsw.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author fernando.barrera
 */
@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nombre;
    private String pass;
    private String nickname;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="amigosuser",
    joinColumns=@JoinColumn(name="nombre_user",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="id_grupos",referencedColumnName="id")
    )
    private Set<Grupo> grupos;
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="salausers",
    joinColumns=@JoinColumn(name="nombre_usuario",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="nombre_sala",referencedColumnName="nombre")
    )
    private Set<Sala> salas;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="usercanciones",
    joinColumns=@JoinColumn(name="nombre_user",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="id_cancion",referencedColumnName="id")
    )
    private Set<Cancion> canciones;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="rolusuarios",
    joinColumns=@JoinColumn(name="nombre_user",referencedColumnName ="nombre"),
    inverseJoinColumns=@JoinColumn(name="rol_id",referencedColumnName="id")
    )
    private Set<Rol> rol;
    
    

    public Usuario(){
        super();
    }
    
    public Usuario(int id,String nombre,String pass,String nickname){

    	this.id=id;
        this.nombre=nombre;
        this.pass=pass;
        this.nickname=nickname;
        this.salas=new HashSet<Sala>();
        this.canciones=new HashSet<Cancion>();
        this.grupos=new HashSet<Grupo>();
        
   
       
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Set<Sala> salas) {
        this.salas = salas;
    }

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Set<Rol> getRol() {
        return rol;
    }

    public void setRol(Set<Rol> rol) {
        this.rol = rol;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    

    
    
    
    
}
