package com.transportApp.transportApp.controller;
import com.transportApp.transportApp.model.ReservationBus;
import com.transportApp.transportApp.service.ReservationBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationBusController {
    
    @Autowired
    private ReservationBusService reservationBusService;

    // Créer une réservation
    @PostMapping
    public ReservationBus createReservation(@RequestBody ReservationBus reservationBus) {
        return reservationBusService.createReservation(reservationBus);
    }

    // Obtenir toutes les réservations
    @GetMapping
    public List<ReservationBus> getAllReservations() {
        return reservationBusService.getAllReservations();
    }

    // Obtenir une réservation par ID
    @GetMapping("/{id}")
    public ReservationBus getReservationById(@PathVariable Long id) {
        return reservationBusService.getReservationById(id);
    }
}
