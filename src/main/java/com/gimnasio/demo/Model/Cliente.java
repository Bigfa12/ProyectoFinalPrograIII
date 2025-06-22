package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;


    private boolean alDia;

    private LocalDate fechaVencimiento;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente")
    private List<Record>records;

    public Cliente(boolean alDia, Usuario usuario, LocalDate fechaVencimiento) {
        this.alDia = alDia;
        this.usuario = usuario;
        this.fechaVencimiento = fechaVencimiento;
    }
}
