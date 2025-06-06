package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PasswordEncoder passwordEncoder1;

    @PostMapping("/login")
    public void inicioSesion(@RequestBody UsuarioIniciaSesionDTO usuario){

    }

    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioRegistroDTO usuario) {
        boolean creado = usuarioServicio.crearUsuario(usuario);
        if (creado) {
            return ResponseEntity.ok("Usuario registrado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
    }
}
