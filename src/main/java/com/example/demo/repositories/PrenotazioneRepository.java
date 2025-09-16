
package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	List<Prenotazione> findByUtenteId(Long utenteId);

	boolean existsByUtenteIdAndEventoId(Long utenteId, Long eventoId);

	//Optional<Prenotazione> findByUtenteIdAndEventoId(Long utenteId, Long eventoId);
}