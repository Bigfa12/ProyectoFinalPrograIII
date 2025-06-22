package com.gimnasio.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanDTO {
    @NotBlank(message = "Especifique el plan")
    @NonNull
    private String plan;

}
