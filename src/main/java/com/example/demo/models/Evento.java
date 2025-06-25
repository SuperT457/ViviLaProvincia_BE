package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
    private Long id;
    
    private String luogo;
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
