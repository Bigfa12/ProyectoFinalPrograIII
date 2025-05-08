package com.gimnasio.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_credencial;
    private long id_usuario;
    private String username;
    private String password;


}
