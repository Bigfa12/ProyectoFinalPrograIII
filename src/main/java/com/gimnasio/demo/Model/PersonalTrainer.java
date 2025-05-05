package com.gimnasio.demo.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class PersonalTrainer extends Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_personal_trainer;
    private String especialidad;
    private List<Cliente> clientesAsignados;


}
