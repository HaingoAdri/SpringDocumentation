package com.example.documentation_officiel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="codes")
public class Error_Codes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code", length=600)
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
