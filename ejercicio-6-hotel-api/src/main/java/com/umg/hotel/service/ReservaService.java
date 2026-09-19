package com.umg.hotel.service;

import com.umg.hotel.exception.ConflictoEstadoException;
import com.umg.hotel.exception.RecursoNoEncontradoException;
import com.umg.hotel.model.EstadoReserva;
import com.umg.hotel.model.Reserva;
import com.umg.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva crear(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> consultarTodas(EstadoReserva estado) {
        if (estado == null) {
            return reservaRepository.findAll();
        }
        return reservaRepository.findAll().stream()
                .filter(r -> r.getEstado() == estado)
                .toList();
    }

    public Reserva consultarPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontro la reserva con id: " + id));
    }

    public Reserva actualizar(Long id, Reserva datos) {
        Reserva existente = consultarPorId(id);
        existente.setNombreCliente(datos.getNombreCliente());
        existente.setHabitacion(datos.getHabitacion());
        existente.setFechaEntrada(datos.getFechaEntrada());
        existente.setFechaSalida(datos.getFechaSalida());
        existente.setEstado(datos.getEstado());
        return existente;
    }

    public Reserva cancelar(Long id) {
        Reserva existente = consultarPorId(id);
        if (existente.getEstado() == EstadoReserva.CANCELADA) {
            throw new ConflictoEstadoException("La reserva con id " + id + " ya se encuentra cancelada");
        }
        existente.setEstado(EstadoReserva.CANCELADA);
        return existente;
    }
}
