package com.app.compta.controller;

import com.app.compta.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Map<String, Long>> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalClients", dashboardService.getTotalClients());
        stats.put("totalFournisseurs", dashboardService.getTotalFournisseurs());
        stats.put("totalFacturesClient", dashboardService.getTotalFacturesClient());
        stats.put("totalFacturesFrs", dashboardService.getTotalFacturesFrs());
        stats.put("totalTransactionsBanque", dashboardService.getTotalTransactionsBanque());
        stats.put("totalTransactionsCaisse", dashboardService.getTotalTransactionsCaisse());
        return ResponseEntity.ok(stats);
    }
}