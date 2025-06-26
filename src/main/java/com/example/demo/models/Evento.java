package com.example.demo.models;

import java.time.LocalDateTime;

//import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String luogo;

    //@JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dataora;
    private float costo;
    private int posti;

    @ManyToOne
    @JoinColumn(name = "organizzatore")
    private Organizzatore organizzatore;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
}
