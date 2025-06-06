package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.Security.UserEntity;
import com.gimnasio.demo.Repository.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicio {
    @Autowired
    private UserRepositorio userRepositorio;

    public UserEntity insertarUser(UserEntity userEntity) {
        return userRepositorio.save(userEntity);
    }

    public void modificarUser(UserEntity userEntity) {
        userRepositorio.save(userEntity);
    }

    public void eliminarUser(UserEntity userEntity) {
        userRepositorio.delete(userEntity);
    }

    public boolean buscarUserPorUsername(String username) {
        return userRepositorio.findByUsername(username);
    }

}
