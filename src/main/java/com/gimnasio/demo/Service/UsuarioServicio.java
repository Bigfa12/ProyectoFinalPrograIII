package com.gimnasio.demo.Service;

import com.gimnasio.demo.Controller.UsuarioController;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Optional<Usuario> buscarUsuarioPorID(Long id){
        return usuarioRepositorio.findById(id);
    }

    public Usuario convertidorDTO(UsuarioRegistroDTO usu){
        Usuario usuu = new Usuario(usu.getUsername(), usu.getEmail(), usu.getContrasena(), usu.getApellido(), usu.getNombre(), usu.getDni(), usu.getDomicilio());
        return usuu;
    }

    public boolean crearUsuario(UsuarioRegistroDTO Dto)
    {
        boolean b=false;
        Usuario usu= convertidorDTO(Dto);

        if(!usuarioRepositorio.existsByEmail(usu.getEmail()) && !usuarioRepositorio.existsByDNI(usu.getDni()) && !usuarioRepositorio.existsByUsername(usu.getUsername())){
            b=true;
            usuarioRepositorio.save(usu);
        }

        return b;
    }

    public void deleteUser(Usuario usuario){usuarioRepositorio.delete(usuario);}



    public void eliminarUsuarioPorID(Long id){
        usuarioRepositorio.deleteById(id);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public void editarUsuario(Long id, Usuario usuario){
        if(usuarioRepositorio.existsById(id)){
            usuarioRepositorio.save(usuario);
        }
    }
    
}
