package com.example.fission_games.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String titulo;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String contenido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private String imagen;
    @ManyToOne
    private User publicador;
}
