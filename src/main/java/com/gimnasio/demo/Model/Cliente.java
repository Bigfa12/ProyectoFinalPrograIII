package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    private boolean alDia;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "nroTarjeta", referencedColumnName = "nroTarjeta")
    private MetodoDePago metodoDePago;

    @OneToMany(mappedBy = "cliente")
    private List<Record>records;


}
