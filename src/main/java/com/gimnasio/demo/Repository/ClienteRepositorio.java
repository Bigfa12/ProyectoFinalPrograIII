package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    boolean existsByUsuario(Usuario usuario);
    Cliente findByIdCliente(Long idCliente);
    Cliente findByUsuario_Id(Long id);
    Cliente findByUsuario(Usuario usuario);
}
