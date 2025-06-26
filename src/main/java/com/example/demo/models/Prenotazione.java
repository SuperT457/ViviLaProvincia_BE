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
@Table(name = "prenotazione")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Prenotazione {
    
    @Id
    private Long id;
    private String username;
    private Long evento_id;
    private LocalDateTime dataora;

    @ManyToOne
    @JoinColumn(name = "evento")
    private Organizzatore evento;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Organizzatore utente;


}
