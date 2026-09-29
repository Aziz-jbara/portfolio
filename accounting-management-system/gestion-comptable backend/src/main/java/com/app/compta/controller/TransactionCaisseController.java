package com.app.compta.controller;

import com.app.compta.entity.TransactionCaisse;
import com.app.compta.service.TransactionCaisseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions-caisse")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionCaisseController {

    private final TransactionCaisseService transactionCaisseService;

    @GetMapping
    public ResponseEntity<List<TransactionCaisse>> getAllTransactions() {
        return ResponseEntity.ok(transactionCaisseService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCaisse> getTransactionById(@PathVariable int id) {
        return ResponseEntity.ok(transactionCaisseService.getTransactionById(id));
    }

    @GetMapping("/facture-client/{factureClientId}")
    public ResponseEntity<List<TransactionCaisse>> getByFactureClient(@PathVariable int factureClientId) {
        return ResponseEntity.ok(transactionCaisseService.getTransactionsByFactureClient(factureClientId));
    }

    @GetMapping("/facture-frs/{factureFrsId}")
    public ResponseEntity<List<TransactionCaisse>> getByFactureFrs(@PathVariable int factureFrsId) {
        return ResponseEntity.ok(transactionCaisseService.getTransactionsByFactureFrs(factureFrsId));
    }

    @PostMapping
    public ResponseEntity<TransactionCaisse> createTransaction(@RequestBody TransactionCaisse transaction) {
        return ResponseEntity.ok(transactionCaisseService.createTransaction(transaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionCaisse> updateTransaction(@PathVariable int id, @RequestBody TransactionCaisse transaction) {
        return ResponseEntity.ok(transactionCaisseService.updateTransaction(id, transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
        transactionCaisseService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}