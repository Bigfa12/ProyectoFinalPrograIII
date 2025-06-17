package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Repository.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServicio {
    @Autowired
    private UserRepositorio userRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insertarUser(User user) {
        userRepositorio.save(user);
    }

    public void modificarUser(User user) {
        userRepositorio.save(user);
    }

    public void eliminarUser(User user) {
        userRepositorio.delete(user);
    }

    public boolean buscarUserPorUsername(String username) {
        return userRepositorio.existsByUsername(username);
    }

    public String encriptarPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
