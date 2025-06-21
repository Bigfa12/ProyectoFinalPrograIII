package com.gimnasio.demo.DTO;

import com.gimnasio.demo.Enums.Ejercicio;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordAltaDTO {
    @NotBlank(message = "El id del cliente no puede estar vacio.")
    private Long id_cliente;

    @NotBlank(message = "El peso no puede estar vacio.")
    @Min(value = 0,message = "El peso no puede ser menor que 0.")
    private float peso;

    @NotBlank(message = "El ejercicio no puede estar vacio.")
    private Ejercicio ejercicio;


}
