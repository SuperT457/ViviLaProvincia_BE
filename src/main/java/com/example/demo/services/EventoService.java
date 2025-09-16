package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.example.demo.models.EventoDTO;
//import com.example.demo.models.Organizzatore;
//import com.example.demo.models.Categoria;

import com.example.demo.models.Evento;
import com.example.demo.repositories.EventoRepository;

import jakarta.transaction.Transactional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

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

    @Transactional
    public void prenotaPosto(Long eventoId){
        System.out.println("Chiamata a prenotaPosto per evento ID: " + eventoId);
        Evento e = getEventoById(eventoId);
        System.out.println("===== \nHO RICEVUTO L'EVENTO: " + e.getTitolo() + " con posti: " + e.getN_posti() + "\n=====");
        if(e.getN_posti() > 0){
            eventoRepository.decrementaPosto(eventoId);
        } else {
            throw new RuntimeException("Non ci sono posti disponibili per questo evento");
        }
    }
}