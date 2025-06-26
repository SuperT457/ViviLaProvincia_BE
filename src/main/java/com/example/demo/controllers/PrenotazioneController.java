
package com.example.demo.controllers;

import com.example.demo.models.Evento;
import com.example.demo.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")



public class PrenotazioneController {
     @Autowired
    private EventoService prenotazioneService;

    @GetMapping
    public List<Evento> getPrenotazioni() {
        return eventoService.getAllPrenotazioni();
    }
}
