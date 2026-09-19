package com.umg.cursos.service;

import com.umg.cursos.exception.RecursoNoEncontradoException;
import com.umg.cursos.model.Curso;
import com.umg.cursos.model.EstadoCurso;
import com.umg.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> consultarTodos(EstadoCurso estado) {
        if (estado == null) {
            return cursoRepository.findAll();
        }
        return cursoRepository.findAll().stream()
                .filter(c -> c.getEstado() == estado)
                .toList();
    }

    public Curso consultarPorCodigo(String codigo) {
        return cursoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro un curso con el codigo: " + codigo));
    }

    public Curso actualizar(Long id, Curso datos) {
        Curso existente = cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro el curso con id: " + id));
        existente.setNombre(datos.getNombre());
        existente.setCodigo(datos.getCodigo());
        existente.setCreditos(datos.getCreditos());
        existente.setEstado(datos.getEstado());
        return existente;
    }

    public void eliminar(Long id) {
        boolean eliminado = cursoRepository.deleteById(id);
        if (!eliminado) {
            throw new RecursoNoEncontradoException("No se encontro el curso con id: " + id);
        }
    }
}
