package com.example.fission_games.controller;

import com.example.fission_games.entity.Comentario;
import com.example.fission_games.entity.User;
import com.example.fission_games.entity.UsuarioVideojuego;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.service.ServicioComentario;
import com.example.fission_games.service.ServicioUsuarioVideojuego;
import com.example.fission_games.service.ServicioVideojuego;
import com.example.fission_games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorVideojuegos {
    @Autowired
    ServicioVideojuego servicioVideojuego;
    @Autowired
    UserService servicioUsuario;
    @Autowired
    ServicioComentario sc;
    @Autowired
    ServicioUsuarioVideojuego servicioUsuarioVideojuego;

    @GetMapping("/videojuegos")
    public String videojuegos(Model model){
        List<Videojuego> listaVideojuegos = servicioVideojuego.findAll();
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

    @GetMapping("/videojuego/{id}")
    public String videojuegoDetalle(@PathVariable long id, Model model){
        model.addAttribute("juego", servicioVideojuego.findById(id));
        ArrayList<Comentario> comentarios = sc.find3();
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nuevoComentario", new Comentario());
        return "videojuegoDetalle";
    }

    @PostMapping("/comentario/add")
    public String guardarComentario(@ModelAttribute("nuevoComentario") Comentario comentario, @RequestParam long id){
        comentario.setFecha(LocalDate.now());
        comentario.setVideojuego(servicioVideojuego.findById(id));
        sc.save(comentario);
        return "redirect:/videojuego/" + comentario.getVideojuego().getId();
    }
}
