package com.gimnasio.demo.Model;

import jakarta.persistence.*;

@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    private boolean alDia;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_trainer",referencedColumnName = "id_trainer")
    private PersonalTrainer trainer;

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAlDia() {
        return alDia;
    }

    public void setAlDia(boolean alDia) {
        this.alDia = alDia;
    }

    public PersonalTrainer getTrainer() {
        return trainer;
    }

    public void setTrainer(PersonalTrainer trainer) {
        this.trainer = trainer;
    }
}
