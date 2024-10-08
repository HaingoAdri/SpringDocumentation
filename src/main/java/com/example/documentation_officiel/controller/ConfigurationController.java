package com.example.documentation_officiel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.documentation_officiel.model.Configuration;
import com.example.documentation_officiel.model.Footer;
import com.example.documentation_officiel.repository.ConfigurationRepository;
import com.example.documentation_officiel.repository.FooterRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/configuration")
@CrossOrigin(origins = {
    "https://dulcet-biscuit-be2122.netlify.app/",
    "https://frolicking-lamington-39fdc3.netlify.app/"
}, allowCredentials = "true")
public class ConfigurationController {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private FooterRepository footerRepository;

    //configuration
    
    @PostMapping("/save_configuration")
    public ResponseEntity<Configuration> save_configuration(@RequestBody Configuration config){
        Configuration configuration = configurationRepository.save(config);
        return ResponseEntity.ok(configuration);
    }

    @GetMapping("/allConfiguration")
    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<Configuration> getConfigurationByName(@RequestParam String name) {
        return configurationRepository.findByTypes(name)
            .map(configuration -> ResponseEntity.ok(configuration))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update_configuration/{types}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable String types, @RequestBody Configuration updatedConfiguration) {
        return configurationRepository.findByTypes(types)
            .map(configuration -> {
                configuration.setTypes(updatedConfiguration.getTypes());
                configuration.setDescription(updatedConfiguration.getDescription());
                Configuration savedConfiguration = configurationRepository.save(configuration);
                return ResponseEntity.ok(savedConfiguration);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_configuration/{types}")
    public ResponseEntity<Void> deleteConfiguration(@PathVariable String types) {
        return configurationRepository.findByTypes(types)
            .map(configuration -> {
                configurationRepository.delete(configuration);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //footer

    @PostMapping("/save_footer")
    public ResponseEntity<Footer> save_footer(@RequestBody Footer footer){
        Footer footers = footerRepository.save(footer);
        return ResponseEntity.ok(footers);
    }

    @GetMapping("/getFooter")
    public ResponseEntity<Footer> getAllFooter() {
        List<Footer> footers = footerRepository.findAll();
        Footer firstFooter = footers.stream().findFirst().orElse(null);
        return ResponseEntity.ok(firstFooter);
    }
    
    @PutMapping("/update_footer/{name}")
    public ResponseEntity<Footer> updateFooter(@PathVariable String name, @RequestBody Footer updatedFooter) {
        return footerRepository.findByTexte(name)
            .map(footer -> {
                footer.setTexte(updatedFooter.getTexte());
                footer.setVersion(updatedFooter.getVersion());
                Footer savedFooter = footerRepository.save(footer);
                return ResponseEntity.ok(savedFooter);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete_footer/{name}")
    public ResponseEntity<Void> deleteFooter(@PathVariable String names) {
        return footerRepository.findByTexte(names)
            .map(footer -> {
                footerRepository.delete(footer);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
