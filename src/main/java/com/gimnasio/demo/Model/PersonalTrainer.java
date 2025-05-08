package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_trainer;
    private String especialidad;

    @OneToMany(mappedBy = "trainer")
    private List<Cliente> clientes;



    }

