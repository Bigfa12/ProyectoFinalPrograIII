package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JsonIgnore
    private long id_ejercicio;

    private String actividad;
    private int series;
    private int repeticiones;
    private float tiempoDeDescanso;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_rutina", referencedColumnName = "id_rutina")
    private Rutina rutina;

    public EjercicioRutina(String actividad, int series, int repeticiones, float tiempoDeDescanso, Rutina rutina) {
        this.actividad = actividad;
        this.series = series;
        this.repeticiones = repeticiones;
        this.tiempoDeDescanso = tiempoDeDescanso;
        this.rutina = rutina;
    }

    public long getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(long id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public float getTiempoDeDescanso() {
        return tiempoDeDescanso;
    }

    public void setTiempoDeDescanso(float tiempoDeDescanso) {
        this.tiempoDeDescanso = tiempoDeDescanso;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public void setIdRutina(long id){
        this.rutina.setId_rutina(id);
    }

    @Override
    public String toString() {
        return "EjercicioRutina{" +
                "id_ejercicio=" + id_ejercicio +
                ", actividad='" + actividad + '\'' +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                ", tiempoDeDescanso=" + tiempoDeDescanso +
                ", rutina=" + rutina.getDia() +
                '}';
    }


}
