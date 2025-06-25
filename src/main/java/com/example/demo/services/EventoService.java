package com.example.demo.services;

import com.example.demo.models.Evento;
import com.example.demo.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }
}