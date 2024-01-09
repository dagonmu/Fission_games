package com.example.fission_games.controller;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.entity.User;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.service.ServicioNoticia;
import com.example.fission_games.service.UserService;
import com.example.fission_games.storage.StorageService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/crud")
public class ControladorCrudNoticias {
    @Autowired
    ServicioNoticia servicioNoticia;
    @Autowired
    UserService userService;
    @Autowired
    StorageService storageService;

    @GetMapping("/noticias")
    public String listaNoticias(Model model, @RequestParam(name="q",required=false) String query){
        List<Noticia> listaNoticias = (query==null) ? servicioNoticia.findAll() : servicioNoticia.buscador(query);
        model.addAttribute("noticias", listaNoticias);
        return "crud/listaNoticias";
    }
    @GetMapping("/noticia/agregar")
    public String agregarNoticia(Model model){
        model.addAttribute("noticia", new Noticia());
        return "crud/formNoticia";
    }
    @PostMapping("/noticia/agregar/post")
    public String postJuego(@Valid @ModelAttribute("formNoticia") Noticia noticia, BindingResult result, @RequestParam("file") MultipartFile file
    , Authentication authentication) {
        User publicador = userService.findByEmail(authentication.getName());
        if (result.hasErrors()) {
            return "crud/formNoticia";
        } else {
            if (!file.isEmpty()) {
                String extension= file.getOriginalFilename();
                extension=extension.substring(extension.lastIndexOf(".") + 1);
                String imagen = storageService.store(file,noticia.getTitulo()+ "-portada." + extension);
                System.out.println("La imagen a guardar es : " + imagen);
                noticia.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
            }
            noticia.setPublicador(publicador);
            noticia.setFecha(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            servicioNoticia.save(noticia);
            return "redirect:/crud/noticias";
        }
    }

    @GetMapping("/noticia/editar/{id}")
    public String editar(@PathVariable long id, Model model){
        Noticia noticia = servicioNoticia.findById(id);
        model.addAttribute("noticia", noticia);
        return "crud/formNoticia";
    }

    @PostMapping("/noticia/editar/post")
    public String postEdit(@Valid @ModelAttribute("formNoticia") Noticia noticia, BindingResult result, @RequestParam("file") MultipartFile file,
                           Authentication authentication) {
        User publicador = userService.findByEmail(authentication.getName());
        if (result.hasErrors()) {
            return "crud/formNoticia";
        } else {
            if (!file.isEmpty()) {
                String extension= file.getOriginalFilename();
                extension=extension.substring(extension.lastIndexOf(".") + 1);
                String imagen = storageService.store(file,noticia.getTitulo()+ "-portada." + extension);
                System.out.println("La imagen a guardar es : " + imagen);
                noticia.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
            }
            noticia.setPublicador(publicador);
            noticia.setFecha(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            servicioNoticia.save(noticia);
            return "redirect:/crud/noticias";
        }
    }

    @GetMapping("/noticia/eliminar/{id}")
    public String delete(@PathVariable long id){
        servicioNoticia.deleteById(id);
        return "redirect:/crud/noticias";
    }
}
