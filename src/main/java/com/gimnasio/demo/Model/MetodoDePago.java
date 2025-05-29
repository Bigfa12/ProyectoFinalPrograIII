package com.gimnasio.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
@Entity
public class MetodoDePago {
    @Id
    private long nroTrajeta;
    private String nombreTitular;
    private Date fechaVencimiento;
    private int cvv;
}
