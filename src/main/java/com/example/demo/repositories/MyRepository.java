package com.example.demo.repositories;

import com.example.demo.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
/* Classe che definisce il repository (database)  */
public interface MyRepository extends JpaRepository<MyTable, Long> {
    
}