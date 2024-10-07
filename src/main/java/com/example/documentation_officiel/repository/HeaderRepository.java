package com.example.documentation_officiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

import com.example.documentation_officiel.model.Header;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Long>{
    List<Header> findBySousCategorie(int sous_categorie);

    @Query(value = "SELECT * FROM view_query order by id", nativeQuery = true)
    List<Object[]> getAllFromView();
}
