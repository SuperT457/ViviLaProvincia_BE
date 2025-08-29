package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Categoria;
import com.example.demo.models.Evento;
import com.example.demo.models.EventoDTO;
import com.example.demo.models.Utente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.UtenteRepository;
import com.example.demo.services.EventoService;

@RestController
@RequestMapping("/api/eventi")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return new ResponseEntity<>(eventi, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> creaEvento(@RequestBody EventoDTO dto) {
        Evento evento = new Evento();

        evento.setTitolo(dto.getTitolo());
        evento.setDescrizione(dto.getDescrizione());
        evento.setLuogo(dto.getLuogo());
        evento.setDataora(dto.getDataora());
        evento.setCosto(dto.getCosto());
        // recupera entità da ID
        Utente organizzatore = utenteRepository.findById(dto.getIdOrganizzatore())
            .orElseThrow(() -> new RuntimeException("Organizzatore non trovato"));
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoria non trovata"));

        evento.setOrganizzatore(organizzatore);
        evento.setCategoria(categoria);

        eventoService.salva(evento);

        return ResponseEntity.ok("Evento creato con successo");
    }

}