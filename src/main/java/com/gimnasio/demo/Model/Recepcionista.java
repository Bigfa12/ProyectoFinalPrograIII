package com.gimnasio.demo.Model;

import jakarta.persistence.*;

@Entity
public class Recepcionista{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_recepcionista;

    @OneToOne
    @JoinColumn(name = "id_empleado",referencedColumnName = "id_empleado")
    private Empleado empleado;

    private boolean manejaCaja;

    public boolean isManejaCaja() {
        return manejaCaja;
    }

    public void setManejaCaja(boolean manejaCaja) {
        this.manejaCaja = manejaCaja;
    }
}
