package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Evento;
/* Classe che definisce il repository (database)  */
public interface EventoRepository extends JpaRepository<Evento, Long> {  
    
}