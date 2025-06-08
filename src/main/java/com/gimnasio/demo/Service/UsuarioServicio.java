package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
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
        Usuario usuu = new Usuario(usu.getEmail(),usu.getApellido(),usu.getNombre(),usu.getDni(),usu.getDomicilio());
        return usuu;
    }

    public boolean crearUsuario(UsuarioRegistroDTO dto)
    {
        if(usuarioRepositorio.existsByEmail(dto.getEmail()) ||
        usuarioRepositorio.existsByDni(dto.getDni()) ||
        userServicio.buscarUserPorUsername(dto.getUsername())){
            return false;
        }

    Usuario usuario = conversorDTO(dto);
        Usuario usuarioSaved = usuarioRepositorio.save(usuario);


        String passENcriptada= userServicio.encriptarPassword(dto.getContrasena());
        User user= new User(dto.getUsername(),passENcriptada,true,usuarioSaved);

        userServicio.insertarUser(user);
       return true;
    }

    /// IMPLEMENTAR EXCEPTION/////////////////////////////////////////////////////////////////////////////////////
    public void eliminarUsuarioPorID(long id)throws UsuarioNoEncontradoException{
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
        List<Tarjeta> tarjetas = tarjetaRepositorio.findByUsuarioId(id);

        if (tarjetas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tarjetas);
    }
}
