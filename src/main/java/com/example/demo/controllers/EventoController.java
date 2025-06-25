package com.example.demo.controllers;

import com.example.demo.models.Evento;
import com.example.demo.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getEventi() {
        return eventoService.getAllEventi();
    }
}