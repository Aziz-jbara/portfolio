package com.app.compta.service;

import com.app.compta.config.JwtUtil;
import com.app.compta.dto.LoginRequest;
import com.app.compta.dto.RegisterRequest;
import com.app.compta.entity.Utilisateur;
import com.app.compta.entity.Role;
import com.app.compta.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(LoginRequest request) {

        System.out.println("EMAIL = " + request.getEmail());
        System.out.println("DTO PASSWORD = " + request.getMotDePasse());

        Utilisateur utilisateur = utilisateurRepository
                .findByUtilisateurEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(
                request.getMotDePasse(),
                utilisateur.getUtilisateurMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return jwtUtil.generateToken(utilisateur.getUtilisateurEmail());
    }

    public Utilisateur register(RegisterRequest request) {

        System.out.println("REGISTER EMAIL = " + request.getEmail());
        System.out.println("REGISTER PASSWORD = " + request.getMotDePasse());
        System.out.println("REGISTER NOM = " + request.getNom());

        if (utilisateurRepository.findByUtilisateurEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUtilisateurNom(request.getNom());
        utilisateur.setUtilisateurPrenom(request.getPrenom());
        utilisateur.setUtilisateurEmail(request.getEmail());
        utilisateur.setUtilisateurMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setUtilisateurTelephone(request.getTelephone());
        utilisateur.setUtilisateurRole(Role.USER);

        return utilisateurRepository.save(utilisateur);
    }
}