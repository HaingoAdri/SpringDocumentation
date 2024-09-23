package com.example.documentation_officiel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Sous_categorie;

@Repository
public interface Sous_categorieRepository extends JpaRepository<Sous_categorie, Long>{
    @Query(value = "SELECT * FROM view_sous_categorie order by id", nativeQuery = true)
    List<Object[]> getAllFromView();
}
