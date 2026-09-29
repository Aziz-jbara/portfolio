package com.app.compta.controller;

import com.app.compta.entity.FactureClientCategorie;
import com.app.compta.service.FactureClientCategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facture-client-categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FactureClientCategorieController {

    private final FactureClientCategorieService factureClientCategorieService;

    @GetMapping
    public ResponseEntity<List<FactureClientCategorie>> getAllCategories() {
        return ResponseEntity.ok(factureClientCategorieService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureClientCategorie> getCategorieById(@PathVariable int id) {
        return ResponseEntity.ok(factureClientCategorieService.getCategorieById(id));
    }

    @PostMapping
    public ResponseEntity<FactureClientCategorie> createCategorie(@RequestBody FactureClientCategorie categorie) {
        return ResponseEntity.ok(factureClientCategorieService.createCategorie(categorie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureClientCategorie> updateCategorie(@PathVariable int id, @RequestBody FactureClientCategorie categorie) {
        return ResponseEntity.ok(factureClientCategorieService.updateCategorie(id, categorie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
        factureClientCategorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}