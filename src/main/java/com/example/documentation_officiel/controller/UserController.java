package com.example.documentation_officiel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8085", allowCredentials = "true")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<?> userProfile() {
        return ResponseEntity.ok("Bienvenue ");
    }

}
