package com.umg.cursos.controller;

import com.umg.cursos.model.Curso;
import com.umg.cursos.model.EstadoCurso;
import com.umg.cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso) {
        Curso creado = cursoService.crear(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> consultarTodos(
            @RequestParam(required = false) EstadoCurso estado) {
        return ResponseEntity.ok(cursoService.consultarTodos(estado));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Curso> consultarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(cursoService.consultarPorCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizar(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
