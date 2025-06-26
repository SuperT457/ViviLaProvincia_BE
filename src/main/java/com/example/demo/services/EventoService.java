package com.example.demo.services;

import com.example.demo.dto.EventoDTO;
import com.example.demo.models.Evento;
import com.example.demo.models.Organizzatore;
import com.example.demo.models.Categoria;
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

     public Evento salva(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Conversione da Evento a DTO
    public EventoDTO convertiInDTO(Evento evento) {
        return new EventoDTO(
            evento.getId(),
            evento.getLuogo(),
            evento.getDataora(),
            evento.getCosto(),
            evento.getPosti(),
            evento.getOrganizzatore() != null ? evento.getOrganizzatore().getId() : null,
            evento.getCategoria() != null ? evento.getCategoria().getId() : null
        );
    }

    // Conversione da DTO a Evento
    public Evento convertiInEntita(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setId(dto.getId());
        evento.setLuogo(dto.getLuogo());
        evento.setDataora(dto.getDataora());
        evento.setCosto(dto.getCosto());
        evento.setPosti(dto.getPosti());

        Organizzatore organizzatore = new Organizzatore();
        organizzatore.setId(dto.getIdOrganizzatore());
        evento.setOrganizzatore(organizzatore);

        Categoria categoria = new Categoria();
        categoria.setId(dto.getIdCategoria());
        evento.setCategoria(categoria);

        return evento;
    }
}