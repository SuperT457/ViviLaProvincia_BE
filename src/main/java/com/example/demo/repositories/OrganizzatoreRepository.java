package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Organizzatore;

/* Classe che definisce il repository (database)  */
public interface OrganizzatoreRepository extends JpaRepository<Organizzatore, Long> {
    
}