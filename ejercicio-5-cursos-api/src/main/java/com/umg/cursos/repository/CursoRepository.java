package com.umg.cursos.repository;

import com.umg.cursos.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public List<Curso> findAll() {
        return cursos;
    }

    public Optional<Curso> findById(Long id) {
        return cursos.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Curso> findByCodigo(String codigo) {
        return cursos.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public Curso save(Curso curso) {
        curso.setId(contadorId.incrementAndGet());
        cursos.add(curso);
        return curso;
    }

    public boolean deleteById(Long id) {
        return cursos.removeIf(c -> c.getId().equals(id));
    }
}
