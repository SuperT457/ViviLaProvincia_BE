package com.example.demo.models;


import java.time.LocalDateTime;

import com.example.demo.models.Evento;
import com.example.demo.models.Utente;
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
    private LocalDateTime dataora;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "utente_id", referencedColumnName = "id")
    private Utente utente;


}
