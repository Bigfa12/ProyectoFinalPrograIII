package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
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

}
