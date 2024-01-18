package com.example.fission_games.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.Type;
@Data
@Entity
public class Videojuego {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String genero;

    @NotEmpty
    private String enlace;

    private String portada;

    @Column(columnDefinition = "TEXT")
    private String controles;
}
