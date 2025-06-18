package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UserServicio;
import com.gimnasio.demo.Service.UsuarioServicio;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import com.gimnasio.demo.Model.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder1;
    @Autowired
    private UserServicio userServicio;
    @Autowired
    private HikariDataSource dataSource;

    @PostMapping("/login")
    public ResponseEntity<?> inicioSesion(@RequestBody UsuarioIniciaSesionDTO usuario){
       UserDetails userDetails;

       try {
           userDetails=userDetailsManager.loadUserByUsername(usuario.getUsername());
       } catch (UsernameNotFoundException e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
       }

       if(passwordEncoder1.matches(usuario.getContrasenia(), userDetails.getPassword()))
       {
           return ResponseEntity.ok("Login exitoso");
       }else
       {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contrasenia incorrecta");
       }
    }

    @PostMapping("/register")
    public void addUsuario(@RequestBody UsuarioRegistroDTO usuario) {
        if (!userDetailsManager.userExists(usuario.getUsername())) {

            usuarioServicio.crearUsuario(usuario);
           jdbcTemplate.update("Insert Into authorities (username,authority) values (?,?)",usuario.getUsername(),"USER");

        }
    }
}
