package com.example.documentation_officiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{
    
}
