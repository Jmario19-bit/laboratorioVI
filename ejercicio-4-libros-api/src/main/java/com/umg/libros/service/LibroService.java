package com.umg.libros.service;

import com.umg.libros.exception.RecursoNoEncontradoException;
import com.umg.libros.model.EstadoLibro;
import com.umg.libros.model.Libro;
import com.umg.libros.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro registrar(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> consultarTodos(EstadoLibro estado) {
        if (estado == null) {
            return libroRepository.findAll();
        }
        return libroRepository.findAll().stream()
                .filter(l -> l.getEstado() == estado)
                .toList();
    }

    public Libro consultarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro un libro con el titulo: " + titulo));
    }

    public Libro actualizar(Long id, Libro datos) {
        Libro existente = libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro el libro con id: " + id));
        existente.setTitulo(datos.getTitulo());
        existente.setAutor(datos.getAutor());
        existente.setIsbn(datos.getIsbn());
        existente.setAnioPublicacion(datos.getAnioPublicacion());
        existente.setEstado(datos.getEstado());
        return existente;
    }

    public void eliminar(Long id) {
        boolean eliminado = libroRepository.deleteById(id);
        if (!eliminado) {
            throw new RecursoNoEncontradoException("No se encontro el libro con id: " + id);
        }
    }
}
