package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Evento;

import jakarta.transaction.Transactional;

/* Classe che definisce il repository (database)  */
public interface EventoRepository extends JpaRepository<Evento, Long> {  
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Evento e SET e.n_posti = e.n_posti - 1 WHERE e.id = :eventoId AND e.n_posti > 0")
    int decrementaPosto(@Param("eventoId") Long eventoId);

    List<Evento> findByOrganizzatoreId(Long organizzatoreId);
}