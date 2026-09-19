package com.umg.hotel.controller;

import com.umg.hotel.model.EstadoReserva;
import com.umg.hotel.model.Reserva;
import com.umg.hotel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva) {
        Reserva creada = reservaService.crear(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> consultarTodas(
            @RequestParam(required = false) EstadoReserva estado) {
        return ResponseEntity.ok(reservaService.consultarTodas(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.consultarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.actualizar(id, reserva));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelar(id));
    }
}
