package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gimnasio.demo.Enums.Ejercicio;
import jakarta.persistence.*;
import lombok.*;
import com.gimnasio.demo.Model.Cliente;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_record;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private float peso;

    @Enumerated(EnumType.STRING)
    private Ejercicio ejercicio;

    public Record(Cliente cliente, float peso, Ejercicio ejercicio) {
        this.cliente = cliente;
        this.peso = peso;
        this.ejercicio = ejercicio;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id_record=" + id_record +
                ", cliente=" + cliente.getUsuario().getNombre() +
                ", peso=" + peso +
                ", ejercicio=" + ejercicio +
                '}';
    }
}
