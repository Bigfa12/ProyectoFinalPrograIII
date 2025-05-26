package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;
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
  //  @PostMapping("/insert")  ///funciones con spring security

  @PostMapping("/register")
  public void addCliente(@RequestBody Usuario usuario) {
      System.out.println(usuario.toString());

      usuarioServicio.crearUsuario(usuario);
  }

  // @GetMapping("/list")
  
    // @postmapping("/login")
    //@getmapping("/profile")

}
