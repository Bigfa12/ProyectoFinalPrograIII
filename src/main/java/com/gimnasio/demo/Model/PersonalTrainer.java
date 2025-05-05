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

    public long getId_personal_trainer() {
        return id_personal_trainer;
    }

    public void setId_personal_trainer(long id_personal_trainer) {
        this.id_personal_trainer = id_personal_trainer;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cliente> getClientesAsignados() {
        return clientesAsignados;
    }

    public void setClientesAsignados(List<Cliente> clientesAsignados) {
        this.clientesAsignados = clientesAsignados;
    }
}
