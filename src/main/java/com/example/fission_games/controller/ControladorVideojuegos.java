package com.example.fission_games.controller;

import com.example.fission_games.entity.User;
import com.example.fission_games.entity.UsuarioVideojuego;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.service.ServicioUsuarioVideojuego;
import com.example.fission_games.service.ServicioVideojuego;
import com.example.fission_games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControladorVideojuegos {
    @Autowired
    ServicioVideojuego servicioVideojuego;
    @Autowired
    UserService servicioUsuario;
    @Autowired
    ServicioUsuarioVideojuego servicioUsuarioVideojuego;

    @GetMapping("/videojuegos")
    public String videojuegos(Model model, @RequestParam(name="q",required=false) String query){
        List<Videojuego> listaVideojuegos = (query==null) ? servicioVideojuego.findAll() : servicioVideojuego.buscador(query);
        model.addAttribute("listaJuegos", listaVideojuegos);
        return "videojuegos";
    }
    @GetMapping("jugar/{id}")
    public String jugar(Model model, @PathVariable long id, Authentication authentication){
        Videojuego videojuego = servicioVideojuego.findById(id);
        User usuario = servicioUsuario.findByEmail(authentication.getName());

        servicioUsuarioVideojuego.nuevoJuegoJugado(usuario, videojuego);

        model.addAttribute("juego", videojuego);
        return "game";
    }
}
