package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.TarjetaNoEncontradaException;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Repository.UserRepositorio;
import com.gimnasio.demo.Service.TarjetaServicio;
import com.gimnasio.demo.Service.UserServicio;
import com.gimnasio.demo.Service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UserServicio userServicio;
    @Autowired
    private UserRepositorio userRepositorio;
    @Autowired
    private TarjetaServicio tarjetaServicio;

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


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarUsuarioPorID(@PathVariable Long id){
        try{
            usuarioServicio.eliminarUsuarioPorID(id);
        }catch(UsuarioNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }


    @GetMapping("/all")
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

    @GetMapping ("usuarios/miPerfil")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> verMiPerfil(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        String perfil=user.toString();

        return ResponseEntity.ok(perfil);
    }

    @PostMapping("usuarios/agregarTarjeta")
    @PreAuthorize("hasAuthority('USER')")
    public void ingresarTarjeta(@RequestBody TarjetaIngresoDTO tarjeta){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        tarjetaServicio.ingresarTarjeta(tarjeta, user.getUsuario());
    }

    ///revisaaaaaaaaaaaaaaaaaar, nose si hay que pasarle el ID////////////////////////////////////////////////////////////
    @DeleteMapping("usuario/eliminarTarjeta/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void eliminarTarjeta(Long id){
        try{
            tarjetaServicio.eliminarTarjeta(id);
        }catch (TarjetaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }
}
