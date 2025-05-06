package com.transportApp.transportApp.controller;

import com.transportApp.transportApp.model.Paiement;
import com.transportApp.transportApp.service.PaiementService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final PaiementService paiementService;

    // ✅ Créer un paiement via JSON body
    @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody PaiementRequest request) {
        try {
            Paiement paiement = paiementService.createPaiement(
                request.getReservationId(),
                request.getMontant(),
                request.getDatePaiement()
            );
            return new ResponseEntity<>(paiement, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtenir tous les paiements associés à une réservation
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<Paiement>> getPaiementsByReservation(@PathVariable Long reservationId) {
        try {
            List<Paiement> paiements = paiementService.getPaiementsByReservation(reservationId);
            return new ResponseEntity<>(paiements, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtenir un paiement par son ID
    @GetMapping("/{paiementId}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long paiementId) {
        try {
            Paiement paiement = paiementService.getPaiementById(paiementId);
            return new ResponseEntity<>(paiement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // ✅ DTO interne pour les requêtes de création
    @Data
    public static class PaiementRequest {
        private Long reservationId;
        private double montant;
        private LocalDate datePaiement;
    }
}
