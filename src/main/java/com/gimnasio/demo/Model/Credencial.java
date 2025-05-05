package com.gimnasio.demo.Model;

import com.gimnasio.demo.Model.Enums.PERMISOS;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_credencial;
    private long id_usuario;
    private String username;
    private String password;
    private PERMISOS permiso;

    public long getId_credencial() {
        return id_credencial;
    }

    public void setId_credencial(long id_credencial) {
        this.id_credencial = id_credencial;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PERMISOS getPermiso() {
        return permiso;
    }

    public void setPermiso(PERMISOS permiso) {
        this.permiso = permiso;
    }
}
