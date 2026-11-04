package mx.edu.utez.MT09_irving_flores_CA2.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestInscribirParticipante {

    @Positive(message = "El id debe ser positivo")
    Long idCurso;

    @NotBlank(message = "El nombre no debe estar vacio")
    String nombre;

    @Email(message = "El correo debe tener formato valido")
    String correo;

    @Positive(message = "La edad debe ser positiva")
    int edad;
}
