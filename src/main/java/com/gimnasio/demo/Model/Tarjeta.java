package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private long id;
=======
    private Long id;
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
    private long nroTrajeta;
    private String nombreTitular;
    private Date fechaVencimiento;
    private int cvv;

<<<<<<< HEAD

=======
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
}
