package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_empleado;

    private double salario;

    private Time horaEntrada;
    private Time horaSalida;

    private Date fechaContratacion;



    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;


    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }
}
