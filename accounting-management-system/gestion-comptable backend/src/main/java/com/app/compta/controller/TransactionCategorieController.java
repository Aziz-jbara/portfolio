package com.app.compta.controller;

import com.app.compta.entity.TransactionCategorie;
import com.app.compta.service.TransactionCategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionCategorieController {

    private final TransactionCategorieService transactionCategorieService;

    @GetMapping
    public ResponseEntity<List<TransactionCategorie>> getAllCategories() {
        return ResponseEntity.ok(transactionCategorieService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategorie> getCategorieById(@PathVariable int id) {
        return ResponseEntity.ok(transactionCategorieService.getCategorieById(id));
    }

    @GetMapping("/nature/{natureId}")
    public ResponseEntity<List<TransactionCategorie>> getCategoriesByNature(@PathVariable int natureId) {
        return ResponseEntity.ok(transactionCategorieService.getCategoriesByNature(natureId));
    }

    @PostMapping
    public ResponseEntity<TransactionCategorie> createCategorie(@RequestBody TransactionCategorie categorie) {
        return ResponseEntity.ok(transactionCategorieService.createCategorie(categorie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionCategorie> updateCategorie(@PathVariable int id, @RequestBody TransactionCategorie categorie) {
        return ResponseEntity.ok(transactionCategorieService.updateCategorie(id, categorie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
        transactionCategorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}