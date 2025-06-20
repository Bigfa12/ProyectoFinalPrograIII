package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Repository.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    public boolean getUserByName(String name) {
        return userRepositorio.existsByUsername(name);
    }

  public User getUserByUsername(String username) {
        return userRepositorio.findByUsername(username);
  }


    public String encriptarPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User findByUsuarioId(Long usuarioId) {
        return userRepositorio.findByUsuarioId(usuarioId);
    }

    public User conseguirUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user= userRepositorio.findByUsername(username);

        return user;
    }
}
