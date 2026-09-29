package com.app.compta.controller;

import com.app.compta.entity.TransactionCategorieNature;
import com.app.compta.service.TransactionCategorieNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorie-natures")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionCategorieNatureController {

    private final TransactionCategorieNatureService transactionCategorieNatureService;

    @GetMapping
    public ResponseEntity<List<TransactionCategorieNature>> getAllNatures() {
        return ResponseEntity.ok(transactionCategorieNatureService.getAllNatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategorieNature> getNatureById(@PathVariable int id) {
        return ResponseEntity.ok(transactionCategorieNatureService.getNatureById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionCategorieNature> createNature(@RequestBody TransactionCategorieNature nature) {
        return ResponseEntity.ok(transactionCategorieNatureService.createNature(nature));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionCategorieNature> updateNature(@PathVariable int id, @RequestBody TransactionCategorieNature nature) {
        return ResponseEntity.ok(transactionCategorieNatureService.updateNature(id, nature));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNature(@PathVariable int id) {
        transactionCategorieNatureService.deleteNature(id);
        return ResponseEntity.noContent().build();
    }
}