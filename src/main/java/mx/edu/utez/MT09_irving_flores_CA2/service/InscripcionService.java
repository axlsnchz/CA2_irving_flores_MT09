package mx.edu.utez.MT09_irving_flores_CA2.service;

import jakarta.transaction.Transactional;
import mx.edu.utez.MT09_irving_flores_CA2.controller.dto.RequestInscribirParticipante;
import mx.edu.utez.MT09_irving_flores_CA2.controller.dto.RequestRegistrarCalificacionDto;
import mx.edu.utez.MT09_irving_flores_CA2.exception.CustomException;
import mx.edu.utez.MT09_irving_flores_CA2.models.curso.Curso;
import mx.edu.utez.MT09_irving_flores_CA2.models.curso.EstadoCurso;
import mx.edu.utez.MT09_irving_flores_CA2.models.inscripcion.EstadoInscripcion;
import mx.edu.utez.MT09_irving_flores_CA2.models.inscripcion.Inscripcion;
import mx.edu.utez.MT09_irving_flores_CA2.repository.CursoRepository;
import mx.edu.utez.MT09_irving_flores_CA2.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionService {

    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(CursoRepository cursoRepository, InscripcionRepository inscripcionRepository) {
        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }


    @Transactional
    public Inscripcion inscribirParticipante(RequestInscribirParticipante payload){
        Curso curso = cursoRepository.findById(payload.getIdCurso())
                .orElseThrow(() -> new CustomException("El curso no existe"));

        if (curso.getEstado() != EstadoCurso.DISPONIBLE){
            throw new CustomException("El curso no esta disponible");
        }

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstadoInscripcion(EstadoInscripcion.ACTIVA);

        cursoRepository.save(curso);

        inscripcion.setCurso(curso);

        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    public Inscripcion cancelarInscripcion(Long id){
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new CustomException("La inscripcion no existe"));

        if (inscripcion.getEstadoInscripcion() != EstadoInscripcion.ACTIVA){
            throw new CustomException("Solo se permite cancelar una inscripcion activa");
        }

        Curso curso = inscripcion.getCurso();

        if (curso.getEstado() != EstadoCurso.EN_CURSO){
            throw new CustomException("No se puede cancelar si ya comenzo");
        }

        inscripcion.setEstadoInscripcion(EstadoInscripcion.CANCELADA);

        cursoRepository.save(curso);
        inscripcion.setCurso(curso);
        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    public Curso cambiarEstado(Long id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CustomException("El curso no existe"));

        if (curso.getEstado() == EstadoCurso.DISPONIBLE){
            curso.setEstado(EstadoCurso.EN_CURSO);
        } else if (curso.getEstado() == EstadoCurso.EN_CURSO){
            curso.setEstado(EstadoCurso.FINALIZADO);
        } else if (curso.getEstado() == EstadoCurso.FINALIZADO) {
            throw new CustomException("No se puede cambiar un curso finalizado");
        }

        return cursoRepository.save(curso);
    }

    @Transactional
    public Inscripcion registrarCalificacion(RequestRegistrarCalificacionDto payload){
        Inscripcion inscripcion = inscripcionRepository.findById(payload.getId())
                .orElseThrow(() -> new CustomException("No existe"));

        Curso curso = inscripcion.getCurso();

        if (curso.getEstado() != EstadoCurso.FINALIZADO){
            throw new CustomException("Solo finalizado");
        }

        if (inscripcion.getEstadoInscripcion() != EstadoInscripcion.ACTIVA){
            throw new CustomException("Solo activa");
        }

        inscripcion.setEstadoInscripcion(EstadoInscripcion.COMPLETADA);

        cursoRepository.save(curso);
        return inscripcionRepository.save(inscripcion);
    }

}
