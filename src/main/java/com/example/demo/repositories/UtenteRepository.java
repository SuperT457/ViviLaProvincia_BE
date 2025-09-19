package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Utente;

import jakarta.transaction.Transactional;
/* Classe che definisce il repository (database)  */
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Utente u SET u.punti = u.punti + 10 WHERE u.id = :utenteId")
    int aggiungiPunti(@Param("utenteId") Long utenteId);
}
