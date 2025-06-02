package com.gimnasio.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    private boolean alDia;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

<<<<<<< HEAD
    @OneToMany
    @JoinColumn(name = "id" ,referencedColumnName = "id")
    private List<Tarjeta> tarjetas;

=======
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
    @OneToMany(mappedBy = "cliente")
    private List<Record>records;


}
