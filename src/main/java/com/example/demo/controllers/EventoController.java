package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Categoria;
import com.example.demo.services.CategoriaService;
import com.example.demo.models.Evento;
import com.example.demo.models.EventoDTO;
import com.example.demo.models.Utente;
import com.example.demo.services.EventoService;
import com.example.demo.services.UtenteService;

@RestController
@RequestMapping("/api/eventi")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CategoriaService categoriaService;


    //HTTP HANDLERS
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return new ResponseEntity<>(eventi, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventoById(@PathVariable("id") Long eventoId){
        Evento evento = eventoService.getEventoById(eventoId);
	    
        return new ResponseEntity<>(evento, HttpStatus.OK); 
    }

    @GetMapping("/organizzatore/{id}")
    public ResponseEntity<?> getEventoByOrganizzatore(@PathVariable("id") Long organizzatoreId){
        List<Evento> eventi = eventoService.getEventoByOrganizzatore(organizzatoreId);
        
        return new ResponseEntity<>(eventi, HttpStatus.OK); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
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

        //organizzatore
        Utente organizzatore = utenteService.getUtenteById(dto.getIdOrganizzatore());
        
        //categoria
        Categoria categoria = categoriaService.getCategoriaById(dto.getIdCategoria());

        evento.setOrganizzatore(organizzatore);
        evento.setCategoria(categoria);

        eventoService.salva(evento);

        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

}
