package com.gimnasio.demo.Model;

import jakarta.persistence.*;

@Entity
public class UsuarioRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario_record;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Record record;

    private int peso;

    public long getId_usuario_record() {
        return id_usuario_record;
    }

    public void setId_usuario_record(long id_usuario_record) {
        this.id_usuario_record = id_usuario_record;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
