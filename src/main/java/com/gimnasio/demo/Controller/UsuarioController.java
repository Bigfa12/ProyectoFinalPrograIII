package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    //@GetMapping("/list")
    //@Postmapping("/login")
    //@Getmapping("/profile")

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder1;


    @PostMapping("/register")
    public void addCliente(@RequestBody Usuario usuario) {
        System.out.println(usuario.toString());

        if(!userDetailsManager.userExists(usuario.getEmail())) {
            UserDetails userDetails = User.builder().
                    username(usuario.getEmail()).
                    password(passwordEncoder1.encode
                            (usuario.getContrasena())).authorities("USER").build();
            userDetailsManager.createUser(userDetails);
            usuarioServicio.crearUsuario(usuario);
        }

    }


}
