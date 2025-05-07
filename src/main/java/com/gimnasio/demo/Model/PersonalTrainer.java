package com.gimnasio.demo.Model;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_trainer;
    private String especialidad;

    @OneToMany
    @JoinColumn(name = "id_trainer",referencedColumnName = "id_trainer")
    private List<Cliente> clientes;

    @OneToOne
    @JoinColumn(name = "id_empleado",referencedColumnName = "id_empleado")
    private Empleado empleado;

    public int getId_trainer() {
        return id_trainer;
    }

    List<Record> records;

    public void setId_trainer(int id_trainer) {
        this.id_trainer = id_trainer;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @OneToMany(mappedBy = "personalTrainer")
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
