package com.example.documentation_officiel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.documentation_officiel.DTO_Authentification.LoginRequest;
import com.example.documentation_officiel.model.Utilisateur;
import com.example.documentation_officiel.repository.UtilisateurRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {
    "https://6704e87de334cf00cd293879--dulcet-biscuit-be2122.netlify.app/",
    "http://localhost:3000"
}, allowCredentials = "true")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository userRepository;

    @GetMapping("/home")
    public String getMethodName(@RequestParam String param) {
        return new String("Hello world");
    }

    @PostMapping("/inscription_utilisateur")
    public ResponseEntity<Utilisateur> insertUtilisateur(@RequestBody Utilisateur user) {
        Utilisateur savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Utilisateur user = userRepository.findByEmail(loginRequest.getEmail())
            .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }
        return ResponseEntity.ok("Connexion réussie, bienvenue " + user.getNom());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Déconnexion réussie");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la déconnexion");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
