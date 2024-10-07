package com.example.documentation_officiel.controller;

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

import java.util.List;

import com.example.documentation_officiel.model.Header;
import com.example.documentation_officiel.repository.HeaderRepository;

@RestController
@RequestMapping("/api/header")
@CrossOrigin(origins = "http://localhost:8085", allowCredentials = "true")
public class HeaderController {
    
    @Autowired
    private HeaderRepository headerRepository;

    @GetMapping("/allHeader")
    public List<Header> getAllheader() {
        return headerRepository.findAll();
    }

    @GetMapping("/allHeader_view")
    public List<Object[]> getAllHeader_view() {
        return headerRepository.getAllFromView();
    }

    @GetMapping("/bySousCategorie/{sousCategorieId}")
    public ResponseEntity<List<Header>> getHeadersBySousCategorie(@PathVariable int sousCategorieId) {
        List<Header> headers = headerRepository.findBySousCategorie(sousCategorieId);
        return ResponseEntity.ok(headers);
    }

    @PostMapping("/save_header")
    public ResponseEntity<Header> save_header(@RequestBody Header cate){
        Header header = headerRepository.save(cate);
        return ResponseEntity.ok(header);
    }

    @PutMapping("/update_header/{id}")
    public ResponseEntity<Header> updateHeader(@PathVariable Long id, @RequestBody Header updatedHeader) {
        return headerRepository.findById(id)
            .map(header -> {
                header.setNom(updatedHeader.getNom());
                header.setTypes(updatedHeader.getTypes());
                header.setDetails(updatedHeader.getDetails());
                header.setSous_categorie(updatedHeader.getSous_categorie());
                Header savedHeader = headerRepository.save(header);
                return ResponseEntity.ok(savedHeader);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_header/{id}")
    public ResponseEntity<Void> deleteHeader(@PathVariable Long id) {
        return headerRepository.findById(id)
            .map(header -> {
                headerRepository.delete(header);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
