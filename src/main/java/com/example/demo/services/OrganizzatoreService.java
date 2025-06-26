package com.example.demo.services;

import com.example.demo.models.Organizzatore;
import com.example.demo.repositories.OrganizzatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizzatoreService {

    @Autowired
    private OrganizzatoreRepository organizzatoreRepository;

    public List<Organizzatore> getAllOrganizzatori() {
        return organizzatoreRepository.findAll();
    }
}