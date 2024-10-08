package com.example.documentation_officiel.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.documentation_officiel.model.Information;
import com.example.documentation_officiel.repository.InformationRepository;

@RestController
@RequestMapping("/api/information")
@CrossOrigin(origins = "https://dulcet-biscuit-be2122.netlify.app/", allowCredentials = "true")
public class InformationController {
    
    @Autowired
    private InformationRepository informationRepository;

    @PostMapping("/save_information")
    public ResponseEntity<Information> save_information(@RequestBody Information infor){
        Information information = informationRepository.save(infor);
        return ResponseEntity.ok(information);
    }

    @GetMapping("/allInformation")
    public List<Information> getAllInformation() {
        return informationRepository.findAll();
    }

    @GetMapping("/search/{titre}")
    public Optional<Information> getMethodName(@PathVariable String titre) {
        Optional<Information> info = informationRepository.findByTitre(titre);
        return info;
    }
    

    @GetMapping("/search")
    public ResponseEntity<Information> getInformationByName(@RequestParam Long id) {
        return informationRepository.findById(id)
            .map(information -> ResponseEntity.ok(information))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update_inforamtion/{id}")
    public ResponseEntity<Information> updateInformation(@PathVariable Long id, @RequestBody Information updatedInformation) {
        return informationRepository.findById(id)
            .map(information -> {
                information.setTitre(updatedInformation.getTitre());
                information.setDetails(updatedInformation.getDetails());
                Information savediInformation = informationRepository.save(information);
                return ResponseEntity.ok(savediInformation);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_information/{id}")
    public ResponseEntity<Void> deleteinformation(@PathVariable Long id) {
        return informationRepository.findById(id)
            .map(information -> {
                informationRepository.delete(information);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
