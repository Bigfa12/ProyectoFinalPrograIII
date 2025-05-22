package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;
@Entity


@Getter
@Setter
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


}
