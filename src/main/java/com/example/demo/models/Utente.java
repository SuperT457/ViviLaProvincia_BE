//Class Utente.java

package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Table(name = "utente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Column(columnDefinition = "enum('u','o') default 'u'")
    private String ruolo = "u";

    private Integer punti;	

    @NotBlank
    @Size(min = 8)
    @JsonIgnore
    private String password;
}
