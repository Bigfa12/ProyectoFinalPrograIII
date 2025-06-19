package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder1;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public void addUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO)
    {
        if(userDetailsManager.userExists(usuarioRegistroDTO.getUsername()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
        }

        Usuario usuario=usuarioServicio.crearUsuario2(usuarioRegistroDTO);

        jdbcTemplate.update("INSERT INTO users(username, password, enabled, id_usuario)VALUES(?,?,?,?)",
                usuarioRegistroDTO.getUsername(), passwordEncoder1.encode(usuarioRegistroDTO.getContrasena()), true, usuario.getId()
                );

        jdbcTemplate.update("INSERT INTO authorities(username, authority)VALUES(?,?)",
                usuarioRegistroDTO.getUsername(), "USER"
                );
    }
}
