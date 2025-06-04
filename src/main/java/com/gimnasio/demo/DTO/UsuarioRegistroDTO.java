package com.gimnasio.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioRegistroDTO {
    @NotBlank(message = "el usuario esta vacio")
    private String username;

    @NotBlank(message = "ingrese una contrasenia")
    private String contrasena;

    @NotBlank(message = "ingrese un nombre")
    private String nombre;

    @NotBlank(message = "ingrese un apellido")
    private String apellido;

    @NotBlank(message = "ingrese un email")
    @Email(message = "el email no es valido")
    private String email;

    @NotBlank(message = "ingrese un domicilio")
    private String domicilio;

    @NotBlank(message = "ingrese un dni")
    @Size(min = 8, max = 8, message = "el dni es invalido")
    private int dni;

}
