package com.app.compta.controller;

import com.app.compta.entity.TransactionSens;
import com.app.compta.service.TransactionSensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sens")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionSensController {

    private final TransactionSensService transactionSensService;

    @GetMapping
    public ResponseEntity<List<TransactionSens>> getAllSens() {
        return ResponseEntity.ok(transactionSensService.getAllSens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionSens> getSensById(@PathVariable int id) {
        return ResponseEntity.ok(transactionSensService.getSensById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionSens> createSens(@RequestBody TransactionSens sens) {
        return ResponseEntity.ok(transactionSensService.createSens(sens));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionSens> updateSens(@PathVariable int id, @RequestBody TransactionSens sens) {
        return ResponseEntity.ok(transactionSensService.updateSens(id, sens));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSens(@PathVariable int id) {
        transactionSensService.deleteSens(id);
        return ResponseEntity.noContent().build();
    }
}