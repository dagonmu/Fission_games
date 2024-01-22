package com.example.fission_games.repository;

import com.example.fission_games.entity.Comentario;
import com.example.fission_games.entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Long> {
    public ArrayList<Comentario> findAll();
    public Comentario findById(long id);
    public ArrayList<Comentario> findByVideojuego(Videojuego videojuego);
    @Query("select c from Comentario c order by c.id DESC limit 3")
    public ArrayList<Comentario> find3();
}
