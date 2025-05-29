package com.gimnasio.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRepositorio extends JpaRepository<PersonalTrainer,Long> {

}
