package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor

public class UsuarioRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario_record;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Record record;

    private int peso;


}
