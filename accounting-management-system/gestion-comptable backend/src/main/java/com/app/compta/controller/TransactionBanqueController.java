package com.app.compta.controller;

import com.app.compta.entity.TransactionBanque;
import com.app.compta.service.TransactionBanqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions-banque")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransactionBanqueController {

    private final TransactionBanqueService transactionBanqueService;

    @GetMapping
    public ResponseEntity<List<TransactionBanque>> getAllTransactions() {
        return ResponseEntity.ok(transactionBanqueService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionBanque> getTransactionById(@PathVariable int id) {
        return ResponseEntity.ok(transactionBanqueService.getTransactionById(id));
    }

    @GetMapping("/facture-client/{factureClientId}")
    public ResponseEntity<List<TransactionBanque>> getByFactureClient(@PathVariable int factureClientId) {
        return ResponseEntity.ok(transactionBanqueService.getTransactionsByFactureClient(factureClientId));
    }

    @GetMapping("/facture-frs/{factureFrsId}")
    public ResponseEntity<List<TransactionBanque>> getByFactureFrs(@PathVariable int factureFrsId) {
        return ResponseEntity.ok(transactionBanqueService.getTransactionsByFactureFrs(factureFrsId));
    }

    @GetMapping("/releve/{releveId}")
    public ResponseEntity<List<TransactionBanque>> getByReleve(@PathVariable int releveId) {
        return ResponseEntity.ok(transactionBanqueService.getTransactionsByReleve(releveId));
    }

    @PostMapping
    public ResponseEntity<TransactionBanque> createTransaction(@RequestBody TransactionBanque transaction) {
        return ResponseEntity.ok(transactionBanqueService.createTransaction(transaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionBanque> updateTransaction(@PathVariable int id, @RequestBody TransactionBanque transaction) {
        return ResponseEntity.ok(transactionBanqueService.updateTransaction(id, transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id) {
        transactionBanqueService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}