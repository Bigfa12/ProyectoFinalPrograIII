package com.gimnasio.demo.Service;

import com.gimnasio.demo.Controller.UsuarioController;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;

    public Optional<Usuario> buscarUsuarioPorID(Long id) throws UsuarioNoEncontradoException{
        Optional<Usuario> usuario;
        if(usuarioRepositorio.existsById(id)){
             usuario=usuarioRepositorio.findById(id);
        }else{
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }

        return usuario;
    }

    public Usuario conversorDTO(UsuarioRegistroDTO usu){
        Usuario usuu = new Usuario(usu.getUsername(), usu.getEmail(), usu.getContrasena(), usu.getApellido(), usu.getNombre(), usu.getDni(), usu.getDomicilio());
        return usuu;
    }

    public boolean crearUsuario(UsuarioRegistroDTO Dto)
    {
        boolean b=false;
        Usuario usu= conversorDTO(Dto);

        if(!usuarioRepositorio.existsByEmail(usu.getEmail()) && !usuarioRepositorio.existsByDni(usu.getDni()) && !usuarioRepositorio.existsByUsername(usu.getUsername())){
            b=true;
            usuarioRepositorio.save(usu);
        }

        return b;
    }

    public void eliminarUsuarioPorID(Long id) throws UsuarioNoEncontradoException{


    /// IMPLEMENTAR EXCEPTION/////////////////////////////////////////////////////////////////////////////////////
    public void eliminarUsuarioPorID(long id){
        if(usuarioRepositorio.existsById(id)){
            usuarioRepositorio.deleteById(id);
        }else{
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }
    }

    public List<Usuario> listarUsuarios(){
       return usuarioRepositorio.findAll();
    }


    public void editarUsuario(Long id, Usuario usuario) throws UsuarioNoEncontradoException{
        if(usuarioRepositorio.existsById(id)){
            usuarioRepositorio.save(usuario);
        }else{
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }
    }

    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(long id) {
        List<Tarjeta> tarjetas = tarjetaRepositorio.findByIdUsuario(id);

        if (tarjetas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tarjetas);
    }
}
