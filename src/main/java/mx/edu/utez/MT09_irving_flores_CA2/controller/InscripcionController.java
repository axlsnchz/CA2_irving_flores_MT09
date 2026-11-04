package mx.edu.utez.MT09_irving_flores_CA2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.MT09_irving_flores_CA2.controller.dto.RequestInscribirParticipante;
import mx.edu.utez.MT09_irving_flores_CA2.models.curso.Curso;
import mx.edu.utez.MT09_irving_flores_CA2.models.inscripcion.Inscripcion;
import mx.edu.utez.MT09_irving_flores_CA2.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripcion")
@CrossOrigin("*")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }


    @PostMapping("/registrar")
    public ResponseEntity<Inscripcion> registrar(@Valid @RequestBody RequestInscribirParticipante payload) {
        return ResponseEntity.ok(inscripcionService.inscribirParticipante(payload));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Inscripcion> cancelar(@PathVariable Long id){
        return ResponseEntity.ok(inscripcionService.cancelarInscripcion(id));
    }


    @PutMapping("/curso/{id}/cambiar-estado")
    public ResponseEntity<Curso> cambiarEstado(@PathVariable Long id){
        return ResponseEntity.ok(inscripcionService.cambiarEstado(id));
    }


//    inscripcionService.inscribirParticipante(payload);

}
