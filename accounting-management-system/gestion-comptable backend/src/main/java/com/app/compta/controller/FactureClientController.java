package com.app.compta.controller;

import com.app.compta.entity.FactureClient;
import com.app.compta.service.FactureClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures-client")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FactureClientController {

    private final FactureClientService factureClientService;

    @GetMapping
    public ResponseEntity<List<FactureClient>> getAllFacturesClient() {
        return ResponseEntity.ok(factureClientService.getAllFacturesClient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureClient> getFactureClientById(@PathVariable int id) {
        return ResponseEntity.ok(factureClientService.getFactureClientById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<FactureClient>> getFacturesByClient(@PathVariable int clientId) {
        return ResponseEntity.ok(factureClientService.getFacturesByClient(clientId));
    }

    @PostMapping
    public ResponseEntity<FactureClient> createFactureClient(@RequestBody FactureClient factureClient) {
        return ResponseEntity.ok(factureClientService.createFactureClient(factureClient));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable int id) {
        byte[] pdf = factureClientService.getFacturePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"facture_" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureClient> updateFactureClient(@PathVariable int id, @RequestBody FactureClient factureClient) {
        return ResponseEntity.ok(factureClientService.updateFactureClient(id, factureClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactureClient(@PathVariable int id) {
        factureClientService.deleteFactureClient(id);
        return ResponseEntity.noContent().build();
    }
}
