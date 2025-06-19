package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String fechaVencimiento;
    private int cvv;
    private int dniTitular;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    public Tarjeta(Long nroTarjeta, String nombreTitular, String fechaVencimiento, int cvv, int dniTitular) {
        this.nroTarjeta = nroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.dniTitular = dniTitular;
    }

}
