package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorie(){
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id){
        Categoria c = categoriaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria non trovata"));
        
        return c;
    }
}
