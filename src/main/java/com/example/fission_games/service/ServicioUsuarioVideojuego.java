package com.example.fission_games.service;

import com.example.fission_games.entity.User;
import com.example.fission_games.entity.UsuarioVideojuego;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.repository.RepositorioUsuarioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuarioVideojuego {
    @Autowired
    RepositorioUsuarioVideojuego repositorioUsuarioVideojuego;

    public List<UsuarioVideojuego> findAllByUsuario(User usuario){
        return repositorioUsuarioVideojuego.findAllByUsuario(usuario);
    }
    public void save(UsuarioVideojuego jugado){
        repositorioUsuarioVideojuego.save(jugado);
    }

    public void nuevoJuegoJugado(User usuario, Videojuego videojuego){
        boolean contenido = false;
        List<UsuarioVideojuego> jugados = repositorioUsuarioVideojuego.findAllByUsuario(usuario);
        for(UsuarioVideojuego juego:jugados){
            if(juego.getVideojuego().equals(videojuego)){
                contenido=true;
            }
        }
        if(!contenido){
            UsuarioVideojuego nuevo = new UsuarioVideojuego(usuario, videojuego);
            repositorioUsuarioVideojuego.save(nuevo);
        }
    }
}
