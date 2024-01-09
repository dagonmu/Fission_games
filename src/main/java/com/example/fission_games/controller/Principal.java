package com.example.fission_games.controller;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.service.ServicioNoticia;
import com.example.fission_games.service.ServicioVideojuego;
import com.example.fission_games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
public class Principal {
    @Autowired
    ServicioVideojuego servicioVideojuego;
    @Autowired
    ServicioNoticia servicioNoticia;

    @GetMapping("/")
    public String home(Model model){
        List<Videojuego> listaVideojuegos = servicioVideojuego.findFirst3ByOrderByIdDesc();
        List<Noticia> listaNoticias = servicioNoticia.findFirst3ByOrderByIdDesc();
        model.addAttribute("listaJuegos", listaVideojuegos);
        model.addAttribute("listaNoticias", listaNoticias);
        return "index";
    }

    @GetMapping("/noticias")
    public String noticias(Model model){
        List<Noticia> listaNoticias = servicioNoticia.findAll();
        model.addAttribute("listaNoticias", listaNoticias);
        return "noticias";
    }
    @GetMapping("/videojuegos")
    public String videojuegos(Model model){
        List<Videojuego> listaVideojuegos = servicioVideojuego.findAll();
        model.addAttribute("listaJuegos", listaVideojuegos);
        return "videojuegos";
    }

    @GetMapping("/panel-de-control")
    public String panel(Model model){
        return "crud/panelControl";
    }
}
