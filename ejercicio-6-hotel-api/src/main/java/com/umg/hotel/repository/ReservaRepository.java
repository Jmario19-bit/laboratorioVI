package com.umg.hotel.repository;

import com.umg.hotel.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public List<Reserva> findAll() {
        return reservas;
    }

    public Optional<Reserva> findById(Long id) {
        return reservas.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public Reserva save(Reserva reserva) {
        reserva.setId(contadorId.incrementAndGet());
        reservas.add(reserva);
        return reserva;
    }
}
