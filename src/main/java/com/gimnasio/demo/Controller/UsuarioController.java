package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
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
    public Optional<Usuario> buscarUsuarioPorID(Long id){
        return usuarioServicio.buscarUsuarioPorID(id);
    }

    @PostMapping
    public void crearUsuario(@RequestBody UsuarioRegistroDTO usuarioRegistroDTO){
        usuarioServicio.crearUsuario(usuarioRegistroDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuarioPorID(@PathVariable Long id){
        usuarioServicio.eliminarUsuarioPorID(id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PostMapping("/{id}")
    public void editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        usuarioServicio.editarUsuario(id, usuario);
    }

    /*
    @GetMapping("/tarjeta/{id}")
    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(Long id){
        return usuarioServicio.listarTarjetasDeUsuario(id);
    }

     */


}
