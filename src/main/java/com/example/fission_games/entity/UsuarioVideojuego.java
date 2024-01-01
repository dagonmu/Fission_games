package com.example.fission_games.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuarioVideojuego{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User usuario;
    @ManyToOne
    private Videojuego videojuego;

    public UsuarioVideojuego(User usuario, Videojuego videojuego){
        this.usuario=usuario;
        this.videojuego=videojuego;
    }
}
