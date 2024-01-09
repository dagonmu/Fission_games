package com.example.fission_games.repository;

import com.example.fission_games.entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioVideojuego extends JpaRepository<Videojuego,Long> {
    public Videojuego findById(long id);
    public List<Videojuego> findAll();
    public List<Videojuego> findFirst3ByOrderByIdDesc();
    public void deleteById(long id);
}
