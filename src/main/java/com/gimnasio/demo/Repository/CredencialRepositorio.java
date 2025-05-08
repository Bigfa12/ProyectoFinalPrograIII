package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialRepositorio extends JpaRepository<Credencial, Long> {

}
