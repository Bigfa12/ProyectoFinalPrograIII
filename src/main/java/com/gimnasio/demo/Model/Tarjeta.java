package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long nroTrajeta;
    private String nombreTitular;
    private Date fechaVencimiento;
    private int cvv;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente clienteTarjeta;


    public Tarjeta(@NotBlank(message = "ingrese un numero de tarjeta") @Size(max = 18, min = 16, message = "la cantidad de numeros no es valida") long nroTrajeta, @NotBlank(message = "ingrese un nombre de titular de la tarjeta") String nombreTitular, @NotBlank(message = "ingrese una fecha de vencimiento") Date fechaVencimiento, @NotBlank(message = "ingrese el codigo de seguridad de la tarjeta") int cvv) {
    }
}
