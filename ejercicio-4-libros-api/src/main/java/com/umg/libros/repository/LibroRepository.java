package com.umg.libros.repository;

import com.umg.libros.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LibroRepository {

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public List<Libro> findAll() {
        return libros;
    }

    public Optional<Libro> findById(Long id) {
        return libros.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    public Optional<Libro> findByTitulo(String titulo) {
        return libros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public Libro save(Libro libro) {
        libro.setId(contadorId.incrementAndGet());
        libros.add(libro);
        return libro;
    }

    public boolean deleteById(Long id) {
        return libros.removeIf(l -> l.getId().equals(id));
    }
}
