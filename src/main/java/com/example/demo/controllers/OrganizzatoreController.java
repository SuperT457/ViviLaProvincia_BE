package com.example.demo.controllers;

import com.example.demo.models.Organizzatore;
import com.example.demo.services.OrganizzatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class OrganizzatoreController {

    @Autowired
    private OrganizzatoreService organizzatoreService;

    @GetMapping
    public List<Organizzatore> getUtenti() {
        return organizzatoreService.getAllUtenti();
    }
}