package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    private Long id;
    private String luogo;
    private LocalDateTime dataora;
    private float costo;
    private int posti;
    private Long idOrganizzatore;
    private Long idCategoria;
}