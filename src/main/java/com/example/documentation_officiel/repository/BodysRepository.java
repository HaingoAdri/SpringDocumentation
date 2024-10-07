package com.example.documentation_officiel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Bodys;

@Repository
public interface BodysRepository extends JpaRepository<Bodys, Long>{
    List<Bodys> findBySousCategorie(int sous_categorie);

    @Query(value = "SELECT * FROM view_body order by id", nativeQuery = true)
    List<Object[]> getAllFromView();
}
