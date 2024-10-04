package com.example.documentation_officiel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.documentation_officiel.model.Error_Codes;

public interface Error_CodesRepository extends JpaRepository<Error_Codes, Long>{
    
}
