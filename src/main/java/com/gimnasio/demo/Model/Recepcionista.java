package com.gimnasio.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recepcionista extends Empleado{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_recepcionista;
    private boolean manejaCaja;

    public long getId_recepcionista() {
        return id_recepcionista;
    }

    public void setId_recepcionista(long id_recepcionista) {
        this.id_recepcionista = id_recepcionista;
    }

    public boolean isManejaCaja() {
        return manejaCaja;
    }

    public void setManejaCaja(boolean manejaCaja) {
        this.manejaCaja = manejaCaja;
    }
}
