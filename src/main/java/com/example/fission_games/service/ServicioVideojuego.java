package com.example.fission_games.service;

import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.repository.RepositorioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioVideojuego {
    @Autowired
    RepositorioVideojuego repositorioVideojuego;

    public Videojuego findById(long id){
        return repositorioVideojuego.findById(id);
    }

    public List<Videojuego> findAll(){
        return repositorioVideojuego.findAll();
    }

    public void save(Videojuego videojuego){
        repositorioVideojuego.save(videojuego);
    }
    public void delete(Videojuego videojuego){
        repositorioVideojuego.delete(videojuego);
    }
    public void deleteById(long id){
        repositorioVideojuego.deleteById(id);
    }
}
