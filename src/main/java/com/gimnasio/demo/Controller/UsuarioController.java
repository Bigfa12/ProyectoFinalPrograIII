package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;
    //@PostMapping("/insert")  ///funciones con spring security

<<<<<<< HEAD
  @PostMapping("/register")
<<<<<<< HEAD
  public void addUser(@RequestBody Usuario usuario) {
      System.out.println(usuario.toString());

=======
  public void addCliente(@RequestBody Usuario usuario) {
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
      usuarioServicio.crearUsuario(usuario);
  }



  // @GetMapping("/list")
  
    // @postmapping("/login")
    //@getmapping("/profile")
=======
    @PostMapping("/register")
    public void addCliente(@RequestBody Usuario usuario) {
        System.out.println(usuario.toString());

        usuarioServicio.crearUsuario(usuario);
    }

    //@GetMapping("/list")
    //@Postmapping("/login")
    //@Getmapping("/profile")
>>>>>>> 52cd14143635ce3955451e954597296f5b56d6da

}
