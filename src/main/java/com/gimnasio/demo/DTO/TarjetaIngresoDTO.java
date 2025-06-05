package com.gimnasio.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarjetaIngresoDTO{
    @NotBlank(message = "ingrese un numero de tarjeta")
    @Size(max = 18, min = 16, message = "la cantidad de numeros no es valida")
    private long nroTrajeta;

    @NotBlank(message = "ingrese un nombre de titular de la tarjeta")
    private String nombreTitular;

    @NotBlank(message = "ingrese una fecha de vencimiento")
    private Date fechaVencimiento;

    @NotBlank(message = "ingrese el codigo de seguridad de la tarjeta")
    private int cvv;
}
