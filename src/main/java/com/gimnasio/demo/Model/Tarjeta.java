package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nroTarjeta;
    private String nombreTitular;
    private YearMonth fechaVencimiento;
    private int cvv;
    private int dniTitular;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    public Tarjeta(Long nroTarjeta, String nombreTitular, YearMonth fechaVencimiento, int cvv, int dniTitular, Usuario usuario) {
        this.nroTarjeta = nroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.dniTitular = dniTitular;
        this.usuario = usuario;
    }

    public Tarjeta(@NotBlank(message = "ingrese un numero de tarjeta") @Size(max = 18, min = 16, message = "la cantidad de numeros no es valida") long nroTrajeta, @NotBlank(message = "ingrese un nombre de titular de la tarjeta") String nombreTitular, @NotBlank(message = "ingrese una fecha de vencimiento") Date fechaVencimiento, @NotBlank(message = "ingrese el codigo de seguridad de la tarjeta") int cvv) {
    }

    public Tarjeta(@NotBlank(message = "ingrese un numero de tarjeta") @Size(max = 18, min = 16, message = "la cantidad de numeros no es valida") long nroTrajeta, @NotBlank(message = "ingrese un nombre de titular de la tarjeta") String nombreTitular, @NotBlank(message = "ingrese una fecha de vencimiento") YearMonth fechaVencimiento, @NotBlank(message = "ingrese el codigo de seguridad de la tarjeta") int cvv, @NotBlank(message = "ingrese el dni del Usuario") @Size(max = 8,min=7,message = "cant invalida") int dni) {
    }
}
