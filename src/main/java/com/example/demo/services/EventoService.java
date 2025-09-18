package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import com.example.demo.models.EventoDTO;
//import com.example.demo.models.Organizzatore;
//import com.example.demo.models.Categoria;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Evento;
import com.example.demo.repositories.EventoRepository;
import com.example.demo.repositories.PrenotazioneRepository;

import jakarta.transaction.Transactional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
    return eventoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("id evento non valido"));
    }

    public Evento salva(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento createEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    @Transactional
    public void prenotaPosto(Long eventoId, Long utenteId) {
        System.out.println("Chiamata a prenotaPosto per evento ID: " + eventoId);
        Evento e = getEventoById(eventoId);
        System.out.println("===== \nHO RICEVUTO L'EVENTO: " + e.getTitolo() + " con posti: " + e.getN_posti() + "\n=====");
        
        if(e.getN_posti() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Non ci sono posti disponibili per questo evento");
        }
        
        boolean giaPrenotato = prenotazioneRepository.existsByUtenteIdAndEventoId(utenteId, eventoId);
        if(giaPrenotato){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente già prenotato per questo evento");
        }
        System.out.println("Posti disponibili prima della prenotazione: " + e.getN_posti());
        eventoRepository.decrementaPosto(eventoId);
    }
}