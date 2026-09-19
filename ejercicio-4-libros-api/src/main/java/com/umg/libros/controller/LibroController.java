package com.umg.libros.controller;

import com.umg.libros.model.EstadoLibro;
import com.umg.libros.model.Libro;
import com.umg.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> registrar(@Valid @RequestBody Libro libro) {
        Libro creado = libroService.registrar(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Libro>> consultarTodos(
            @RequestParam(required = false) EstadoLibro estado) {
        return ResponseEntity.ok(libroService.consultarTodos(estado));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Libro> consultarPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(libroService.consultarPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.actualizar(id, libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
