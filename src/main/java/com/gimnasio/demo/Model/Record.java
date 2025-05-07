package com.gimnasio.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_record;
    private String nombreEjercicio;
    @OneToMany
    private List<UsuarioRecord> records;

    public long getId_record() {
        return id_record;
    }

    public void setId_record(long id_record) {
        this.id_record = id_record;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public List<UsuarioRecord> getRecords() {
        return records;
    }

    public void setRecords(List<UsuarioRecord> records) {
        this.records = records;
    }
}
