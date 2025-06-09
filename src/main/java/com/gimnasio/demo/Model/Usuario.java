package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL)
    private List<Tarjeta> tarjetas;

    private String nombre;
    private String apellido;
    private String email;
    private String domicilio;
    private int dni;


    public Usuario(String email,String apellido, String nombre, int dni, String domicilio) {
       this.email = email;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
    }

}
