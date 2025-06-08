package com.gimnasio.demo.DTO;

import com.gimnasio.demo.Enums.Ejercicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordDTO {
    private String nombre;
    private String apellido;
    private float peso;
    private Ejercicio ejercicio;
}
