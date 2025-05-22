package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
