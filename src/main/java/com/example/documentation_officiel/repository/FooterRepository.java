package com.example.documentation_officiel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Footer;



@Repository
public interface FooterRepository extends JpaRepository<Footer, Long>{
    Optional<Footer> findByTexte(String texte);
}
