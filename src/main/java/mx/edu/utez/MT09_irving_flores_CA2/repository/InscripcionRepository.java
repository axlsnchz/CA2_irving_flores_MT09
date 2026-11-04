package mx.edu.utez.MT09_irving_flores_CA2.repository;

import mx.edu.utez.MT09_irving_flores_CA2.models.inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
