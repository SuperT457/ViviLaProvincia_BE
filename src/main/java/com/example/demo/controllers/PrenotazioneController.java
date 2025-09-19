package com.example.demo.controllers;

import com.example.demo.models.Prenotazione;
import com.example.demo.services.EventoService;
import com.example.demo.services.PrenotazioneService;
import com.example.demo.services.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.PrenotazioneDTO;
import com.example.demo.models.Utente;
import com.example.demo.models.Evento;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@CrossOrigin(origins = "http://localhost:4200")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }
    
    @GetMapping("/utente/{utenteId}")
    public List<Prenotazione> getPrenotazioniByUserId(@PathVariable("utenteId") Long utenteId){
    	return prenotazioneService.getPrenotazioniUtente(utenteId);
    }

    @PostMapping
    public ResponseEntity<?> createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        Prenotazione p = new Prenotazione();
        System.out.println(prenotazioneDTO);
        System.out.println("Creando prenotazione per utente ID: " + prenotazioneDTO.getUtente_id() + " e evento ID: " + prenotazioneDTO.getEvento_id());
        
        // Decrementa il numero di posti disponibili
        eventoService.prenotaPosto(prenotazioneDTO.getEvento_id(), prenotazioneDTO.getUtente_id()); // all'interno del service è gestita l'eccezione se non ci sono posti 

        Utente u = utenteService.getUtenteById(prenotazioneDTO.getUtente_id());
        Evento e = eventoService.getEventoById(prenotazioneDTO.getEvento_id());

        p.setUtente(u); 
        p.setEvento(e);

        prenotazioneService.createPrenotazione(p);
        utenteService.assegnaPunti(u.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
