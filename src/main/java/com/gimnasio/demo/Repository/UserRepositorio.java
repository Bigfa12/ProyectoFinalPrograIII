package com.gimnasio.demo.Repository;

import com.gimnasio.demo.DTO.UsuarioIniciaSesionDTO;
import com.gimnasio.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorio extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
}
