package com.example.demo.models;

import java.lang.annotation.Inherited;

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
    @Inheritedprivate Long id;
    private String email;
    private String username;
    private String password;
    private String tel;
}