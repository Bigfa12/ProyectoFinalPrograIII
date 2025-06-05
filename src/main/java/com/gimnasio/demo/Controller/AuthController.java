package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder1;

    @PostMapping("/login")
    public void inicioSesion(@RequestBody UsuarioIniciaSesionDTO usuario){

    }

    @PostMapping("/register")
    public void addUsuario(@RequestBody UsuarioRegistroDTO usuario) {
        if (!userDetailsManager.userExists(usuario.getEmail())) {
            UserDetails userDetails = User.builder().
                    username(usuario.getEmail()).
                    password(passwordEncoder1.encode
                            (usuario.getContrasena())).authorities("USER").build();
            userDetailsManager.createUser(userDetails);
            usuarioServicio.crearUsuario(usuario);
        }
    }
}
