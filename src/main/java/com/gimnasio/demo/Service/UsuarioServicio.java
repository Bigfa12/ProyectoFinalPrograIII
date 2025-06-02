package com.gimnasio.demo.Service;

import com.gimnasio.demo.Controller.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Optional<Usuario> buscarUsuarioPorID(Long id){
        return usuarioRepositorio.findById(id);
    }

    public void crearUsuario(Usuario usuario)
    {
        usuarioRepositorio.save(usuario);
    }

<<<<<<< HEAD
    public void deleteUser(Usuario usuario){usuarioRepositorio.delete(usuario);}


=======
    public void eliminarUsuarioPorID(Long id){
        usuarioRepositorio.deleteById(id);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public void editarUsuario(Long id, Usuario usuario){

    }
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
    
}
