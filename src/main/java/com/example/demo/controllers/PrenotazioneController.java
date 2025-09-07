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


import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
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

    @PostMapping
    public ResponseEntity<?> createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        Prenotazione p = new Prenotazione();

        Utente u = utenteService.getUtenteById(prenotazioneDTO.getUtente_id());

        Evento e = eventoService.getEventoById(prenotazioneDTO.getEvento_id());

        p.setUtente(u);
        p.setEvento(e);

        prenotazioneService.createPrenotazione(p);

        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
}