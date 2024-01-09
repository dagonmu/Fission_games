package com.example.fission_games.controller;

import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.service.ServicioVideojuego;
import com.example.fission_games.storage.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/crud")
public class ControladorCrudJuegos {
    @Autowired
    ServicioVideojuego servicioVideojuego;
    @Autowired
    StorageService storageService;

    @GetMapping("/videojuegos")
    public String listaJuegos(Model model){
        List<Videojuego> listaJuegos = servicioVideojuego.findAll();
        model.addAttribute("juegos", listaJuegos);
        return "crud/listaJuegos";
    }
    @GetMapping("/juego/agregar")
    public String agregarJuego(Model model){
        model.addAttribute("juego", new Videojuego());
        return "crud/formJuego";
    }
    @PostMapping("/juego/agregar/post")
    public String postJuego(@Valid @ModelAttribute("formJuego") Videojuego juego, BindingResult result, @RequestParam("file")MultipartFile file) {
        if (result.hasErrors()) {
            return "crud/formJuego";
        } else {
            if (!file.isEmpty()) {
                String extension= file.getOriginalFilename();
                extension=extension.substring(extension.lastIndexOf(".") + 1);
                String imagen = storageService.store(file,juego.getTitulo()+ "-portada." + extension);
                System.out.println("La imagen a guardar es : " + imagen);
                juego.setPortada(MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
            }
            servicioVideojuego.save(juego);
            return "redirect:/crud/videojuegos";
        }
    }

    @GetMapping("/juego/editar/{id}")
    public String editar(@PathVariable long id, Model model){
        Videojuego juego = servicioVideojuego.findById(id);
        model.addAttribute("juego", juego);
        return "crud/formJuego";
    }

    @PostMapping("/juego/editar/post")
    public String postEdit(@Valid @ModelAttribute("formJuego") Videojuego juego, BindingResult result, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "crud/formJuego";
        } else {
            if (!file.isEmpty()) {
                String extension= file.getOriginalFilename();
                extension=extension.substring(extension.lastIndexOf(".") + 1);
                String imagen = storageService.store(file,juego.getTitulo()+ "-portada." + extension);
                System.out.println("La imagen a guardar es : " + imagen);
                juego.setPortada(MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
            }
            servicioVideojuego.save(juego);
            return "redirect:/crud/videojuegos";
        }
    }

    @GetMapping("/juego/eliminar/{id}")
    public String delete(@PathVariable long id){
        servicioVideojuego.deleteById(id);
        return "redirect:/crud/videojuegos";
    }
}
