package com.gimnasio.demo.Model;

import com.gimnasio.demo.Enums.Ejercicio;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_record;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private float peso;

    @Enumerated(EnumType.STRING)
    private Ejercicio ejercicio;


}
