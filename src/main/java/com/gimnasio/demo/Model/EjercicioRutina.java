package com.gimnasio.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjercicioRutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_ejercicio;

    private String actividad;
    private int series;
    private int repeticiones;
    private float tiempoDeDescanso;

    public EjercicioRutina(String actividad, int series, int repeticiones, float tiempoDeDescanso) {
        this.actividad = actividad;
        this.series = series;
        this.repeticiones = repeticiones;
        this.tiempoDeDescanso = tiempoDeDescanso;
    }
}
