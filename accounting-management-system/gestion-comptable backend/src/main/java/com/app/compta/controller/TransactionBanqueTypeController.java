package com.app.compta.controller;

import com.app.compta.entity.TransactionBanqueType;
import com.app.compta.service.TransactionBanqueTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/banque-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionBanqueTypeController {

    private final TransactionBanqueTypeService transactionBanqueTypeService;

    @GetMapping
    public ResponseEntity<List<TransactionBanqueType>> getAllTypes() {
        return ResponseEntity.ok(transactionBanqueTypeService.getAllTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionBanqueType> getTypeById(@PathVariable int id) {
        return ResponseEntity.ok(transactionBanqueTypeService.getTypeById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionBanqueType> createType(@RequestBody TransactionBanqueType type) {
        return ResponseEntity.ok(transactionBanqueTypeService.createType(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionBanqueType> updateType(@PathVariable int id, @RequestBody TransactionBanqueType type) {
        return ResponseEntity.ok(transactionBanqueTypeService.updateType(id, type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable int id) {
        transactionBanqueTypeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}