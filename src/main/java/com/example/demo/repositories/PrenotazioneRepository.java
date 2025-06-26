
package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    
}
