package com.example.documentation_officiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.documentation_officiel.model.Header;

public interface HeaderRepository extends JpaRepository<Header, Long>{
    
}
