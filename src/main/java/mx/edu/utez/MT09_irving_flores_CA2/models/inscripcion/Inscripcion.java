package mx.edu.utez.MT09_irving_flores_CA2.models.inscripcion;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.MT09_irving_flores_CA2.models.curso.Curso;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreParticipante;
    private int edadParticipante;
    private String correo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInscripcion;
    private Double calificacion = null;

    @Enumerated(value = EnumType.STRING)
    private EstadoInscripcion estadoInscripcion;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}
