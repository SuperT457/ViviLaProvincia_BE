package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

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

    private String titolo;
    private String descrizione;
    private String luogo;
    private LocalDateTime dataora;
    private Double costo;

    @Column(name = "n_posti")
    private int nPosti;

    @ManyToOne
    @JoinColumn(name = "organizzatore", referencedColumnName = "id")
    private Utente organizzatore;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private Categoria categoria;

    //Getters e Setters
}
