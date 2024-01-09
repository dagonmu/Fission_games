package com.example.fission_games.controller;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.service.ServicioNoticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class ControladorNoticias {
    @Autowired
    ServicioNoticia servicioNoticia;

    @GetMapping("/noticias")
    public String noticias(Model model, @RequestParam(name="q",required=false) String query){
        List<Noticia> listaNoticias = (query==null) ? servicioNoticia.findAll() : servicioNoticia.buscador(query);
        model.addAttribute("listaNoticias", listaNoticias);
        return "noticias";
    }

    @GetMapping("/noticias/{id}")
    public String noticiaDetalle(@PathVariable long id, Model model){
        model.addAttribute("noticia", servicioNoticia.findById(id));
        return "noticiaDetalle";
    }
}
