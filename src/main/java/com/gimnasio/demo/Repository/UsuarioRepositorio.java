package com.gimnasio.demo.Repository;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByDni(int dni);
    boolean existsByUsername(String usu);
}
