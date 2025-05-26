package com.gimnasio.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void crearUsuario(Usuario usuario)
    {
        usuarioRepositorio.save(usuario);
    }
    
}
