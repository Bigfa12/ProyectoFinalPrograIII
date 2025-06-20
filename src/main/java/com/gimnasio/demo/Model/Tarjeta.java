package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

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
    private int dniTitular;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    public Tarjeta(Long nroTarjeta, String nombreTitular, String fechaVencimiento, int dniTitular) {
        this.nroTarjeta = nroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaVencimiento = fechaVencimiento;
        this.dniTitular = dniTitular;
    }



}
