package com.example.documentation_officiel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.documentation_officiel.model.Error_Codes;
import com.example.documentation_officiel.repository.Error_CodesRepository;

@RestController
@RequestMapping("/api/codes")

public class Error_CodesController {
    @Autowired
    private Error_CodesRepository errorCodesRepository;

    // Obtenir tous les codes d'erreur
    @GetMapping("/allCodes")
    public List<Error_Codes> getAllCodes() {
        return errorCodesRepository.findAll();
    }

    // Obtenir la vue des codes
    @GetMapping("/allCodes_view")
    public List<Object[]> getAllCodes_view() {
        return errorCodesRepository.getAllFromView();
    }

    // Obtenir les codes par sous-catégorie
    @GetMapping("/bySousCategorie/{sousCategorieId}")
    public ResponseEntity<List<Error_Codes>> getCodesBySousCategorie(@PathVariable int sousCategorieId) {
        List<Error_Codes> codes = errorCodesRepository.findBySousCategorie(sousCategorieId);
        return ResponseEntity.ok(codes);
    }

    // Sauvegarder un nouveau code d'erreur
    @PostMapping("/save_code")
    public ResponseEntity<Error_Codes> saveCode(@RequestBody Error_Codes code) {
        Error_Codes savedCode = errorCodesRepository.save(code);
        return ResponseEntity.ok(savedCode);
    }

    // Mettre à jour un code d'erreur
    @PutMapping("/update_code/{id}")
    public ResponseEntity<Error_Codes> updateCode(@PathVariable Long id, @RequestBody Error_Codes updatedCode) {
        return errorCodesRepository.findById(id)
            .map(code -> {
                code.setCode(updatedCode.getCode());
                code.setDetails(updatedCode.getDetails());
                code.setSous_categorie(updatedCode.getSous_categorie());
                Error_Codes savedCode = errorCodesRepository.save(code);
                return ResponseEntity.ok(savedCode);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un code d'erreur
    @DeleteMapping("/delete_code/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        return errorCodesRepository.findById(id)
            .map(code -> {
                errorCodesRepository.delete(code);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
