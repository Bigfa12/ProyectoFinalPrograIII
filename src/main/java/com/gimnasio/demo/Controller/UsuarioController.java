package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Usuario> buscarUsuarioPorID(Long id){
        try{
            return usuarioServicio.buscarUsuarioPorID(id);
        }catch (UsuarioNoEncontradoException e){
            System.out.println(e.getMessage());
        }

      return Optional.empty();
    }

    @PostMapping
    public void crearUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO){
        usuarioServicio.crearUsuario(usuarioRegistroDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarUsuarioPorID(@PathVariable Long id){
        try{
            usuarioServicio.eliminarUsuarioPorID(id);
        }catch(UsuarioNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        try{
            usuarioServicio.editarUsuario(id, usuario);
        }catch (UsuarioNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/tarjeta/{id}")
    //Solo usuario hasrole usuario
    //esto hay que ver si dejarlo, porque no vamos a guardar nada al final creo
    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(Long id){
        return usuarioServicio.listarTarjetasDeUsuario(id);
    }


}
