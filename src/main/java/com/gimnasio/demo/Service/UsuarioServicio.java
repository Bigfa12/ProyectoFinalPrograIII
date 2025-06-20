package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import com.gimnasio.demo.Repository.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;
    @Autowired
    private UserServicio userServicio;
    @Autowired
    private UserRepositorio userRepositorio;

    public Optional<Usuario> buscarUsuarioPorID(Long id) throws UsuarioNoEncontradoException {
        Optional<Usuario> usuario;

        if (id == null) {
            throw new UsuarioNoEncontradoException("El ID del usuario no puede ser null");
        }

        if (usuarioRepositorio.existsById(id)) {
            usuario = usuarioRepositorio.findById(id);
        } else {
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }

        return usuario;
    }


    public Usuario conversorDTO(UsuarioRegistroDTO usu) {

        Usuario usuu = new Usuario(usu.getEmail(), usu.getApellido(), usu.getNombre(), usu.getDni(), usu.getDomicilio());
        return usuu;
    }

    public boolean crearUsuario(UsuarioRegistroDTO dto) {
        boolean b = false;
        Usuario usu = conversorDTO(dto);

        User user = new User(dto.getUsername(), userServicio.encriptarPassword(dto.getContrasena()), true, usu);
        if (!usuarioRepositorio.existsByEmail(usu.getEmail()) && !usuarioRepositorio.existsByDni(usu.getDni()) && !userServicio.getUserByName(dto.getUsername())) {
            b = true;
            usuarioRepositorio.save(usu);
            user.setUsuario(usu);
            userServicio.insertarUser(user);
        }

        return b;
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }


    public void editarUsuario(Long id, Usuario usuario) throws UsuarioNoEncontradoException {
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.save(usuario);
        } else {
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }
    }

    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(long id) {
        List<Tarjeta> tarjetas = tarjetaRepositorio.findByUsuarioId(id);

        if (tarjetas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tarjetas);
    }

    public void updateUsuario(Usuario u){
        usuarioRepositorio.save(u);
    }





}
