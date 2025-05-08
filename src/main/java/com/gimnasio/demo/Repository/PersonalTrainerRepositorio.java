package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.PersonalTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTrainerRepositorio extends JpaRepository<PersonalTrainer,Long> {

}
