package edu.eci.arsw.config;

import edu.eci.arsw.entities.Usuario;
import edu.eci.arsw.persistence.UsuarioRepository;
import edu.eci.arsw.services.client.impl.ExceptionServiciosReserva;
import edu.eci.arsw.services.client.impl.ServiciosSoundShareImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
	private ServiciosSoundShareImpl services;
    

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {

    	System.out.println("xd"+" "+nick);
        Usuario usuario = usuarioRepository.findByNickname(nick);
        System.out.println("xd"+" "+usuario.getPass());
        String encodedPassword = new BCryptPasswordEncoder().encode(usuario.getPass());
        usuario.setPass(encodedPassword);
        try {
			System.out.println(((services.getUsersBans()).contains(usuario.getNickname())));
		} catch (ExceptionServiciosReserva e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			if (logger == null || (services.getUsersBans()).contains(usuario.getNickname())){
			    logger.error("Error en el login, no existe el usuario "+ nick+ " en el sistema");
			    throw new UsernameNotFoundException("Error en el login, no existe el usuario "+ nick+ " en el sistema");
			}
		} catch (ExceptionServiciosReserva e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<GrantedAuthority> authorities = usuario.getRol()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getValor()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getNickname(),usuario.getPass(),true,true,true,true,authorities);
    }
}
