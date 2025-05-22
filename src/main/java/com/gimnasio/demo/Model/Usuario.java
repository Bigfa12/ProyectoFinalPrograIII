package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;
@Entity



@AllArgsConstructor
@NoArgsConstructor
@Builder

public  class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String email;
    private String contrasena;
    private String nombreUsuario;
    private int dni;

    public Usuario(String nombreUsuario,int dni, String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
        this.nombreUsuario = nombreUsuario;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", dni=" + dni +
                '}';
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
