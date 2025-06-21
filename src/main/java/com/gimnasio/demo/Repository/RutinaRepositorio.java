package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Enums.Dia;
import com.gimnasio.demo.Model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutinaRepositorio extends JpaRepository<Rutina, Long> {
    boolean existsByDia(Dia dia);
    Rutina findByDia(Dia dia);
    List<Rutina> findAllByDia(Dia dia);
}
