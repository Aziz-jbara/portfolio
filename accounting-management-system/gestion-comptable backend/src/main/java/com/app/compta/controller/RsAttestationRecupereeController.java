package com.app.compta.controller;

import com.app.compta.entity.RsAttestationRecuperee;
import com.app.compta.service.RsAttestationRecupereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rs-attestations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RsAttestationRecupereeController {

    private final RsAttestationRecupereeService rsAttestationRecupereeService;

    @GetMapping
    public ResponseEntity<List<RsAttestationRecuperee>> getAllRsAttestations() {
        return ResponseEntity.ok(rsAttestationRecupereeService.getAllRsAttestations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RsAttestationRecuperee> getRsAttestationById(@PathVariable int id) {
        return ResponseEntity.ok(rsAttestationRecupereeService.getRsAttestationById(id));
    }

    @PostMapping
    public ResponseEntity<RsAttestationRecuperee> createRsAttestation(@RequestBody RsAttestationRecuperee rs) {
        return ResponseEntity.ok(rsAttestationRecupereeService.createRsAttestation(rs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RsAttestationRecuperee> updateRsAttestation(@PathVariable int id, @RequestBody RsAttestationRecuperee rs) {
        return ResponseEntity.ok(rsAttestationRecupereeService.updateRsAttestation(id, rs));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRsAttestation(@PathVariable int id) {
        rsAttestationRecupereeService.deleteRsAttestation(id);
        return ResponseEntity.noContent().build();
    }
}