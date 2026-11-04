package mx.edu.utez.MT09_irving_flores_CA2.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegistrarCalificacionDto {

    @Positive(message = "el id debe ser positiva")
    Long id;

    @Min(value = 0, message = "La calificacion minima es 0")
    @Max(value = 10, message = "La calificacion maxima es 10")
    @NotNull(message = "la calificacion no puede estar vacia")
    Double calificacion;
}
