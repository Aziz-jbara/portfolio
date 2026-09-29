package com.app.compta.controller;

import com.app.compta.entity.TransactionEtat;
import com.app.compta.service.TransactionEtatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionEtatController {

    private final TransactionEtatService transactionEtatService;

    @GetMapping
    public ResponseEntity<List<TransactionEtat>> getAllEtats() {
        return ResponseEntity.ok(transactionEtatService.getAllEtats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEtat> getEtatById(@PathVariable int id) {
        return ResponseEntity.ok(transactionEtatService.getEtatById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionEtat> createEtat(@RequestBody TransactionEtat etat) {
        return ResponseEntity.ok(transactionEtatService.createEtat(etat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionEtat> updateEtat(@PathVariable int id, @RequestBody TransactionEtat etat) {
        return ResponseEntity.ok(transactionEtatService.updateEtat(id, etat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtat(@PathVariable int id) {
        transactionEtatService.deleteEtat(id);
        return ResponseEntity.noContent().build();
    }
}