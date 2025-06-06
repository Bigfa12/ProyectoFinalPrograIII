package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.Security.UserEntity;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder1;

    public Optional<Usuario> buscarUsuarioPorID(Long id) throws UsuarioNoEncontradoException{
        Optional<Usuario> usuario;
        if(usuarioRepositorio.existsById(id)){
             usuario=usuarioRepositorio.findById(id);
        }else{
            throw new UsuarioNoEncontradoException("ese usuario no existe");
        }

        return usuario;
    }


    public Usuario convertidorDTO(UsuarioRegistroDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO.getEmail(), usuarioDTO.getApellido(), usuarioDTO.getNombre(), usuarioDTO.getDni(), usuarioDTO.getDomicilio());
        return usuario;
    }

    public boolean crearUsuario(UsuarioRegistroDTO dto) {
        Usuario usuario = convertidorDTO(dto);

        String encodedPassword = passwordEncoder1.encode(dto.getContrasena());

        UserEntity userEntity= null;

        boolean existeEmail = usuarioRepositorio.existsByEmail(usuario.getEmail());
        boolean existeDni = usuarioRepositorio.existsByDni(usuario.getDni());
        boolean existeUsername = userServicio.buscarUserPorUsername(dto.getUsername());

        if (!existeEmail && !existeDni && !existeUsername) {
            usuarioRepositorio.save(usuario);
            userEntity.setUsuario(usuario);
            userServicio.insertarUser(userEntity);
            return true;
        }
        return false;
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

    /*

    public Optional<List<Tarjeta>> listarTarjetasDeUsuario(long id) {
        List<Tarjeta> tarjetas = tarjetaRepositorio.findByIdUsuario(id);

        if (tarjetas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tarjetas);
    }

     */
}
