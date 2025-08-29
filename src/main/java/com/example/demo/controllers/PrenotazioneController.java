package com.example.demo.controllers;

import com.example.demo.models.Prenotazione;
import com.example.demo.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.PrenotazioneDTO;
import com.example.demo.models.Utente;
import com.example.demo.repositories.UtenteRepository;
import com.example.demo.models.Evento;
import com.example.demo.repositories.EventoRepository;


import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @PostMapping
    public ResponseEntity<?> createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        Prenotazione p = new Prenotazione();

        Utente u = utenteRepository.findById(prenotazioneDTO.getUtente_id())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Evento e = eventoRepository.findById(prenotazioneDTO.getEvento_id())
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        p.setUtente(u);
        p.setEvento(e);

        prenotazioneService.createPrenotazione(p);

        return ResponseEntity.ok("Prenotazione creata con successo");
    }
}