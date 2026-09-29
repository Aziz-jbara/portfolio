package com.app.compta.controller;

import com.app.compta.entity.TransactionsReleve;
import com.app.compta.service.TransactionsReleveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/releves")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionsReleveController {

    private final TransactionsReleveService transactionsReleveService;

    @GetMapping
    public ResponseEntity<List<TransactionsReleve>> getAllReleves() {
        return ResponseEntity.ok(transactionsReleveService.getAllReleves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsReleve> getReleveById(@PathVariable int id) {
        return ResponseEntity.ok(transactionsReleveService.getReleveById(id));
    }

    @GetMapping("/annee/{annee}")
    public ResponseEntity<List<TransactionsReleve>> getRelevesByAnnee(@PathVariable int annee) {
        return ResponseEntity.ok(transactionsReleveService.getRelevesByAnnee(annee));
    }

    @GetMapping("/mois/{mois}")
    public ResponseEntity<List<TransactionsReleve>> getRelevesByMois(@PathVariable String mois) {
        return ResponseEntity.ok(transactionsReleveService.getRelevesByMois(mois));
    }

    @PostMapping
    public ResponseEntity<TransactionsReleve> createReleve(@RequestBody TransactionsReleve releve) {
        return ResponseEntity.ok(transactionsReleveService.createReleve(releve));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionsReleve> updateReleve(@PathVariable int id, @RequestBody TransactionsReleve releve) {
        return ResponseEntity.ok(transactionsReleveService.updateReleve(id, releve));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReleve(@PathVariable int id) {
        transactionsReleveService.deleteReleve(id);
        return ResponseEntity.noContent().build();
    }
}