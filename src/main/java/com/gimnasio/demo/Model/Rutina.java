package com.gimnasio.demo.Model;

import com.gimnasio.demo.Enums.Dia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rutina;

    @Enumerated(EnumType.STRING)
    private Dia dia;

    @OneToMany(mappedBy = "id_ejercicio", cascade = CascadeType.ALL)
    private List<EjercicioRutina> ejercicios;

    public Rutina(Dia dia, List<EjercicioRutina> ejercicios) {
        this.dia = dia;
        this.ejercicios = ejercicios;
    }
}
