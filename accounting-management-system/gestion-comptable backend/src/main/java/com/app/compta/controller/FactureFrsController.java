package com.app.compta.controller;

import com.app.compta.entity.FactureFrs;
import com.app.compta.service.FactureFrsService;
import com.app.compta.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures-frs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FactureFrsController {

    private final FactureFrsService factureFrsService;
    private final PdfGenerator pdfGenerator;

    @GetMapping
    public ResponseEntity<List<FactureFrs>> getAllFacturesFrs() {
        return ResponseEntity.ok(factureFrsService.getAllFacturesFrs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureFrs> getFactureFrsById(@PathVariable int id) {
        return ResponseEntity.ok(factureFrsService.getFactureFrsById(id));
    }

    @GetMapping("/fournisseur/{fournisseurId}")
    public ResponseEntity<List<FactureFrs>> getFacturesByFournisseur(@PathVariable int fournisseurId) {
        return ResponseEntity.ok(factureFrsService.getFacturesByFournisseur(fournisseurId));
    }

    @PostMapping
    public ResponseEntity<FactureFrs> createFactureFrs(@RequestBody FactureFrs factureFrs) {
        return ResponseEntity.ok(factureFrsService.createFactureFrs(factureFrs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureFrs> updateFactureFrs(@PathVariable int id, @RequestBody FactureFrs factureFrs) {
        return ResponseEntity.ok(factureFrsService.updateFactureFrs(id, factureFrs));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactureFrs(@PathVariable int id) {
        factureFrsService.deleteFactureFrs(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable int id) {
        FactureFrs facture = factureFrsService.getFactureFrsById(id);
        byte[] pdf = pdfGenerator.generateFactureFrsPdf(facture);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"facture-frs-" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}