package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepositorio extends JpaRepository <Tarjeta, Long> {

}
