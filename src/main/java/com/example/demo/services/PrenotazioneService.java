package com.example.demo.services;

import com.example.demo.models.Prenotazione;
import com.example.demo.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione createPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniUtente(Long utenteId){
    	return prenotazioneRepository.findByUtenteId(utenteId);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}
