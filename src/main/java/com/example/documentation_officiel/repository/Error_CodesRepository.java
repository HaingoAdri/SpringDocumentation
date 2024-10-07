package com.example.documentation_officiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.documentation_officiel.model.Error_Codes;

@Repository
public interface Error_CodesRepository extends JpaRepository<Error_Codes, Long>{
    List<Error_Codes> findBySousCategorie(int sous_categorie);

    @Query(value = "SELECT * FROM view_codes order by id", nativeQuery = true)
    List<Object[]> getAllFromView();
}
