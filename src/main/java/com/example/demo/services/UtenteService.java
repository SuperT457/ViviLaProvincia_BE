package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Utente;
import com.example.demo.models.UtenteDTO;
import com.example.demo.repositories.UtenteRepository;

import jakarta.transaction.Transactional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteById(Long id){
        Utente u = utenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organizzatore non trovato"));
        
        return u;
    }

    public Utente login(UtenteDTO utenteDTO){
        Utente check = utenteRepository.findByUsername(utenteDTO.getUsername());

        System.out.println("============================================");
        System.out.println("Utente da verificare: " + check);
        System.out.println("Utente ricevuto: " + utenteDTO);
        System.out.println("============================================");

        if (check == null || !check.getPassword().equals(utenteDTO.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Username o password errati");
        }

        return check;
    }

    public Utente register(UtenteDTO newUtente){
        System.out.println(newUtente);
        if (newUtente.getUsername() == null || newUtente.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username e password sono obbligatori");
        }

        if ( utenteRepository.findByUsername(newUtente.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username già esistente");
        }

        Utente utente = new Utente();
        utente.setUsername(newUtente.getUsername());
        utente.setPassword(newUtente.getPassword());
        utente.setEmail(newUtente.getEmail());
        utente.setPunti(0);

        return utenteRepository.save(utente);
    }

    @Transactional
    public void assegnaPunti(Long utenteId){
        utenteRepository.aggiungiPunti(utenteId);
    }
}