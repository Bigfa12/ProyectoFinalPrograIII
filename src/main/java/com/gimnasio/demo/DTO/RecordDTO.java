package com.gimnasio.demo.DTO;

import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Model.Record;
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


    public RecordDTO(Record record) {
        this.nombre = record.getCliente().getUsuario().getNombre();
        this.apellido = record.getCliente().getUsuario().getApellido();
        this.peso = record.getPeso();
        this.ejercicio = record.getEjercicio();
    }


}
