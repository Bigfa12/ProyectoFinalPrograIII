package com.gimnasio.demo.Repository;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.Model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TarjetaRepositorio extends JpaRepository <Tarjeta, Long> {
    boolean existsByNroTrajeta(long nroTarjeta);
}
