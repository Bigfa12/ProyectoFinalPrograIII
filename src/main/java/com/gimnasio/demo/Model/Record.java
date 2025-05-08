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
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_record;
    private String nombreEjercicio;
    @OneToMany
    private List<UsuarioRecord> records;


}
