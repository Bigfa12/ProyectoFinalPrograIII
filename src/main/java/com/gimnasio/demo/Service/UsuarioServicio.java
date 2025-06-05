package com.gimnasio.demo.Service;

import com.gimnasio.demo.Controller.UsuarioController;
import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.User;
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
    private UsuarioServicio usuarioServicio;

    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;
    @Autowired
    private UserServicio userServicio;

    public Optional<Usuario> buscarUsuarioPorID(Long id){
        return usuarioRepositorio.findById(id);
    }

    public Usuario convertidorDTO(UsuarioRegistroDTO usu){
        Usuario usuu = new Usuario(usu.getEmail(), usu.getApellido(), usu.getNombre(), usu.getDni(), usu.getDomicilio());
        return usuu;
    }

    public boolean crearUsuario(UsuarioRegistroDTO dto)
    {
        boolean b=false;
        Usuario usu= convertidorDTO(dto);
        User user = new User(dto.getUsername(),dto.getContrasena(),true,usu);
        if(!usuarioRepositorio.existsByEmail(usu.getEmail()) && !usuarioRepositorio.existsByDni(usu.getDni()) && !userServicio.buscarUserPorUsername(dto.getUsername())){
            b=true;
            usuarioRepositorio.save(usu);
            user.setUsuario(usu);
            userServicio.insertarUser(user);
        }

        return b;
    }

    public void deleteUser(Usuario usuario){usuarioRepositorio.delete(usuario);}

    /// IMPLEMENTAR EXCEPTION/////////////////////////////////////////////////////////////////////////////////////
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

    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(long id) {
        List<Tarjeta> tarjetas = tarjetaRepositorio.findByIdUsuario(id);

        if (tarjetas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tarjetas);
    }


    
}
