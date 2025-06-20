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
import org.springframework.http.HttpStatus;
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

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> buscarUsuarioPorID(@PathVariable Long id){
        try{
            Optional<Usuario> usuario = usuarioServicio.buscarUsuarioPorID(id);
            return ResponseEntity.ok(usuario);
        }catch (UsuarioNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/admin/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PostMapping("/tarjeta/ingresar")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> ingresarTarjeta(@RequestBody TarjetaIngresoDTO tarjeta){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        boolean existe;

        existe = tarjetaServicio.ingresarTarjeta(tarjeta, user.getUsuario());
        if (existe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya tiene ingresada esta tarjeta.");
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Tarjeta ingresada correctamente.");
        }
    }

    @DeleteMapping("/tarjeta/eliminar/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> eliminarTarjeta(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        Usuario usuario = user.getUsuario();
        List<Tarjeta> tarjetas = usuario.getTarjetas();

        boolean existe = false;
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getId().equals(id)) {
                existe = true;
            }
        }

        if (existe){

            usuario.getTarjetas().removeIf(tarjeta -> tarjeta.getId().equals(id));
            usuarioServicio.updateUsuario(usuario);
            return ResponseEntity.ok("Tarjeta eliminada correctamente.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tarjetas cargadas con ese id.");
        }
    }


    @GetMapping("/tarjeta/misTarjetas")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> listarTarjetasDeUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);
        Usuario usuario = user.getUsuario();


            return ResponseEntity.ok(usuario.getTarjetas());

    }

    @GetMapping ("/miPerfil")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> verMiPerfil(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        return ResponseEntity.ok(user);
    }




}
