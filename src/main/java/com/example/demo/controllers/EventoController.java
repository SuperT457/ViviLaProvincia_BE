package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Evento;
import com.example.demo.models.EventoDTO;
import com.example.demo.services.EventoService;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // GET tutti eventi come DTO
    @GetMapping
    public List<Evento> getEventi() {
        /*
        List<Evento> eventi = eventoService.getAllEventi(); 
        // converto la lista di Entità in lista di DTO
        return eventi.stream()
                .map(eventoService::convertiInDTO)
                .collect(Collectors.toList());
        */
        return eventoService.getAllEventi();
    }

    // POST per creare un nuovo evento
    @PostMapping
    public ResponseEntity<EventoDTO> creaEvento(@RequestBody EventoDTO eventoDTO) {
        Evento evento = eventoService.convertiInEntita(eventoDTO);
        Evento salvato = eventoService.salva(evento);
        EventoDTO risposta = eventoService.convertiInDTO(salvato);
        return new ResponseEntity<EventoDTO>(risposta, HttpStatus.CREATED);
    }
}