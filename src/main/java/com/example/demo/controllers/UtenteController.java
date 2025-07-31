package com.example.demo.controllers;

import com.example.demo.models.Utente;
import com.example.demo.models.UtenteDTO;
import com.example.demo.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Utente> getUtenti() {
        return utenteService.getAllUtenti();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UtenteDTO utenteDTO){
        try {
            Utente logged = utenteService.login(utenteDTO);
            return ResponseEntity.ok(logged);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } 
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UtenteDTO newUtente) {
        try{
            Utente createdUtente = utenteService.register(newUtente);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUtente);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}