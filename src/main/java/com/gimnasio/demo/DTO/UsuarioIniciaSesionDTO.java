package com.gimnasio.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioIniciaSesionDTO {
    @NotBlank(message = "ingrese un nombre de usuario")
    private String username;

    @NotBlank(message = "ingrese una contrasenia")
    private String contrasenia;

}
