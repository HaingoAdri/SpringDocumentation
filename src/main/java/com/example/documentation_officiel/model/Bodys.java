package com.example.documentation_officiel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="body")
public class Bodys {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom", length=600)
    private String nom;

    @Column(name="types", length=600)
    private String types;

    @Column(name="details", length=15000)
    private String details;

    @Column(name="sous_categorie")
    private int sousCategorie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getSous_categorie() {
        return sousCategorie;
    }

    public void setSous_categorie(int sous_categorie) {
        this.sousCategorie = sous_categorie;
    }

    
}
