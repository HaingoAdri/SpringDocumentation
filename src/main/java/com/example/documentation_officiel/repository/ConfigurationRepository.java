package com.example.documentation_officiel.repository;

import org.springframework.stereotype.Repository;

import com.example.documentation_officiel.model.Configuration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{
    Optional<Configuration> findByTypes(String types);
}
