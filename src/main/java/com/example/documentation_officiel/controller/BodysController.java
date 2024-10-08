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

import com.example.documentation_officiel.model.Bodys;
import com.example.documentation_officiel.repository.BodysRepository;

import java.util.List;


@RestController
@RequestMapping("/api/body")
@CrossOrigin(origins = {
    "https://dulcet-biscuit-be2122.netlify.app/",
    "http://localhost:3000"
}, allowCredentials = "true")
public class BodysController {

    @Autowired
    private BodysRepository bodyRepository; // Inject the Body repository

    @GetMapping("/allBody")
    public List<Bodys> getAllBody() {
        return bodyRepository.findAll(); // Retrieve all body entities
    }

    @GetMapping("/allBody_view")
    public List<Object[]> getAllBodyView() {
        return bodyRepository.getAllFromView(); // You can implement this in your repository
    }

    @GetMapping("/bySousCategorie/{sousCategorieId}")
    public ResponseEntity<List<Bodys>> getBodyBySousCategorie(@PathVariable int sousCategorieId) {
        List<Bodys> bodies = bodyRepository.findBySousCategorie(sousCategorieId); // Implement this method in the repository
        return ResponseEntity.ok(bodies);
    }

    @PostMapping("/save_body")
    public ResponseEntity<Bodys> saveBody(@RequestBody Bodys body) {
        Bodys savedBody = bodyRepository.save(body);
        return ResponseEntity.ok(savedBody);
    }

    @PutMapping("/update_body/{id}")
    public ResponseEntity<Bodys> updateBody(@PathVariable Long id, @RequestBody Bodys updatedBody) {
        return bodyRepository.findById(id)
            .map(body -> {
                body.setNom(updatedBody.getNom());
                body.setTypes(updatedBody.getTypes());
                body.setDetails(updatedBody.getDetails());
                body.setSous_categorie(updatedBody.getSous_categorie());
                Bodys savedBody = bodyRepository.save(body);
                return ResponseEntity.ok(savedBody);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_body/{id}")
    public ResponseEntity<Void> deleteBody(@PathVariable Long id) {
        return bodyRepository.findById(id)
            .map(body -> {
                bodyRepository.delete(body); // Delete the body entity
                return ResponseEntity.noContent().<Void>build(); // Return 204 No Content
            })
            .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }
}
