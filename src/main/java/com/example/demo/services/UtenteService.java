package com.example.demo.services;

import com.example.demo.models.Utente;
import com.example.demo.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.models.UtenteDTO;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente login(UtenteDTO utenteDTO){
        Utente check = utenteRepository.findByUsername(utenteDTO.getUsername());

        System.out.println("============================================");
        System.out.println("Utente da verificare: " + check);
        System.out.println("Utente ricevuto: " + utenteDTO);
        System.out.println("============================================");

        if (check == null || !check.getPassword().equals(utenteDTO.getPassword())){
            throw new RuntimeException("Username o password errati");
        }

        return check;
    }

    public Utente register(UtenteDTO newUtente){
        System.out.println(newUtente);
        if (newUtente.getUsername() == null || newUtente.getPassword() == null) {
            throw new RuntimeException("Username e password sono obbligatori");
        }

        if ( utenteRepository.findByUsername(newUtente.getUsername()) != null) {
            throw new RuntimeException("Username già esistente");
        }

        Utente utente = new Utente();
        utente.setUsername(newUtente.getUsername());
        utente.setPassword(newUtente.getPassword());
        utente.setEmail(newUtente.getEmail());

        return utenteRepository.save(utente);
    }
}