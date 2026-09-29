package com.app.compta.controller;

import com.app.compta.entity.Devise;
import com.app.compta.service.DeviseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devises")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DeviseController {

    private final DeviseService deviseService;

    @GetMapping
    public ResponseEntity<List<Devise>> getAllDevises() {
        return ResponseEntity.ok(deviseService.getAllDevises());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devise> getDeviseById(@PathVariable int id) {
        return ResponseEntity.ok(deviseService.getDeviseById(id));
    }

    @PostMapping
    public ResponseEntity<Devise> createDevise(@RequestBody Devise devise) {
        return ResponseEntity.ok(deviseService.createDevise(devise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devise> updateDevise(@PathVariable int id, @RequestBody Devise devise) {
        return ResponseEntity.ok(deviseService.updateDevise(id, devise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevise(@PathVariable int id) {
        deviseService.deleteDevise(id);
        return ResponseEntity.noContent().build();
    }
}