package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    private String titolo;
    private String descrizione;
    private String luogo;
    private LocalDateTime dataora;
    private double costo;
    private int n_posti;
    private Long idOrganizzatore;
    private Long idCategoria;
    private String image_url;

    // getters e setters
}
