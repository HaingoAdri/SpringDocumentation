package com.example.documentation_officiel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    Optional<Information> findByTitre(String titre);
}
