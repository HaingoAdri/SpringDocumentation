package com.example.documentation_officiel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.documentation_officiel.model.Sous_categorie;
import com.example.documentation_officiel.repository.Sous_categorieRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/sous_categorie")
@CrossOrigin(origins = {
    "https://dulcet-biscuit-be2122.netlify.app/",
    "https://frolicking-lamington-39fdc3.netlify.app/"
}, allowCredentials = "true")
public class Sous_categorieController {
    
    @Autowired
    private Sous_categorieRepository sous_categorieRepository;

    @PostMapping("/save_Sous_categorie")
    public ResponseEntity<Sous_categorie> save_Sous_categorie(@RequestBody Sous_categorie cate){
        Sous_categorie categorie = sous_categorieRepository.save(cate);
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/allSous_categorie")
    public List<Sous_categorie> getAllSous_categorie() {
        return sous_categorieRepository.findAll();
    }

    @GetMapping("/allSous_categorie_view")
    public List<Object[]> getAllSous_categorie_view() {
        return sous_categorieRepository.getAllFromView();
    }

    @GetMapping("/categorie/{categorie}")
    public List<Sous_categorie> getSousCategoriesByCategorie(@PathVariable int categorie) {
        return sous_categorieRepository.findByCategorie(categorie);
    }

    @GetMapping("/search")
    public ResponseEntity<Sous_categorie> getSous_categorieByName(@RequestParam Long id) {
        return sous_categorieRepository.findById(id)
            .map(sous_categorie -> ResponseEntity.ok(sous_categorie))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update_Sous_categorie/{id}")
    public ResponseEntity<Sous_categorie> updateSous_categorie(@PathVariable Long id, @RequestBody Sous_categorie updatedSous_categorie) {
        return sous_categorieRepository.findById(id)
            .map(sous_categorie -> {
                sous_categorie.setNom(updatedSous_categorie.getNom());
                sous_categorie.setDetails(updatedSous_categorie.getDetails());
                sous_categorie.setUrl(updatedSous_categorie.getUrl());
                sous_categorie.setMethode(updatedSous_categorie.getMethode());
                sous_categorie.setCategorie(updatedSous_categorie.getCategorie());
                Sous_categorie savedSous_categorie = sous_categorieRepository.save(sous_categorie);
                return ResponseEntity.ok(savedSous_categorie);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete_Sous_categorie/{id}")
    public ResponseEntity<Void> deleteSous_categorie(@PathVariable Long id) {
        return sous_categorieRepository.findById(id)
            .map(sous_categorie -> {
                sous_categorieRepository.delete(sous_categorie);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
