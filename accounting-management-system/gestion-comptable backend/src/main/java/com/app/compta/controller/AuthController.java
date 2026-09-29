package com.app.compta.controller;

import com.app.compta.dto.LoginRequest;
import com.app.compta.dto.RegisterRequest;
import com.app.compta.entity.Role;
import com.app.compta.entity.Utilisateur;
import com.app.compta.repository.UtilisateurRepository;
import com.app.compta.service.AuthService;
import com.app.compta.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        Utilisateur saved = authService.register(request);

        String token = jwtUtil.generateToken(saved.getUtilisateurEmail());

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}