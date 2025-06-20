package com.gimnasio.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    @JsonIgnore
    private String password;
    private boolean enabled;

    @Setter
    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;



}
