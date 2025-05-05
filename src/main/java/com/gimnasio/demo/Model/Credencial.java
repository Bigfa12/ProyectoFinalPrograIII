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
}
