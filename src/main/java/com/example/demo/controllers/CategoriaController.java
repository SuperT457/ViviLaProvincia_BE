package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demo.models.Categoria;
import com.example.demo.services.CategoriaService;

@RestController
@RequestMapping("/api/categorie")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getCategorie(){
        return categoriaService.getAllCategorie();
    }
}
