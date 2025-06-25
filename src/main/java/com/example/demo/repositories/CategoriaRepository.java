package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Utente;
/* Classe che definisce il repository (database)  */
public interface CategoriaRepository extends JpaRepository<Utente, Long> {
    
}