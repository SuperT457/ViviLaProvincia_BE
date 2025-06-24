package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utente{
    @Id
    private Long id;
    
    private String email;
    private String username;
    private String password;
    private String tel;
}