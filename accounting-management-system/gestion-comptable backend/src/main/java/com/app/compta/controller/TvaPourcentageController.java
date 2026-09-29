package com.app.compta.controller;

import com.app.compta.entity.TvaPourcentage;
import com.app.compta.service.TvaPourcentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tva")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TvaPourcentageController {

    private final TvaPourcentageService tvaPourcentageService;

    @GetMapping
    public ResponseEntity<List<TvaPourcentage>> getAllTva() {
        return ResponseEntity.ok(tvaPourcentageService.getAllTvaPourcentages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvaPourcentage> getTvaById(@PathVariable int id) {
        return ResponseEntity.ok(tvaPourcentageService.getTvaPourcentageById(id));
    }

    @PostMapping
    public ResponseEntity<TvaPourcentage> createTva(@RequestBody TvaPourcentage tva) {
        return ResponseEntity.ok(tvaPourcentageService.createTvaPourcentage(tva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TvaPourcentage> updateTva(@PathVariable int id, @RequestBody TvaPourcentage tva) {
        return ResponseEntity.ok(tvaPourcentageService.updateTvaPourcentage(id, tva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTva(@PathVariable int id) {
        tvaPourcentageService.deleteTvaPourcentage(id);
        return ResponseEntity.noContent().build();
    }
}