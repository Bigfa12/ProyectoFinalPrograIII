package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Enums.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepositorio extends JpaRepository<Record, Long> {
    List<Record> findByEjercicioOrderByPesoDesc(Ejercicio ejercicio);

}
