package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gimnasio.demo.Enums.Dia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id_rutina;

    @Enumerated(EnumType.STRING)
    private Dia dia;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EjercicioRutina> ejercicios;

    public Rutina(Dia dia, List<EjercicioRutina> ejercicios) {
        this.dia = dia;
        this.ejercicios = ejercicios;
    }

    public long getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(long id_rutina) {
        this.id_rutina = id_rutina;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public List<EjercicioRutina> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioRutina> ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "id_rutina=" + id_rutina +
                ", dia=" + dia +
                ", ejercicios=" + ejercicios +
                '}';
    }
}
