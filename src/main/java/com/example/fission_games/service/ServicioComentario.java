package com.example.fission_games.service;

import com.example.fission_games.entity.Comentario;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.repository.RepositorioComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioComentario {
    @Autowired
    RepositorioComentario rc;

    public ArrayList<Comentario> findAll(){
        return rc.findAll();
    }

    public Comentario findById(long id){
        return rc.findById(id);
    }

    public ArrayList<Comentario> findByPelicula(Videojuego videojuego){
        return rc.findByVideojuego(videojuego);
    }

    public ArrayList<Comentario> find3(){
        return rc.find3();
    }

    public Comentario save(Comentario comentario){
        return rc.save(comentario);
    }
    public void delete(Comentario comentario){ rc.delete(comentario); }

    public void deleteById(long id){rc.deleteById(id);}
}
