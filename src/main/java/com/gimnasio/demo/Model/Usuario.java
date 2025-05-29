package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
public  class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;
    private String email;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String domicilio;
    private int dni;

    public Usuario(String email, String contrasena, String apellido, String nombre, int dni, String domicilio) {
        this.email = email;
        this.contrasena = contrasena;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
    }
}
