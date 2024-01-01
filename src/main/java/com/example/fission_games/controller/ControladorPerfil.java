package com.example.fission_games.controller;

import com.example.fission_games.entity.User;
import com.example.fission_games.entity.UsuarioVideojuego;
import com.example.fission_games.service.ServicioUsuarioVideojuego;
import com.example.fission_games.service.UserService;
import com.example.fission_games.storage.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/perfil")
public class ControladorPerfil {
    @Autowired
    UserService servicioUsuario;
    @Autowired
    StorageService storageService;
    @Autowired
    ServicioUsuarioVideojuego servicioUsuarioVideojuego;

    @GetMapping("")
    public String perfil(Model model, Authentication authentication){

        User usuario=servicioUsuario.findByEmail(authentication.getName());
        List<UsuarioVideojuego> listaJugados = servicioUsuarioVideojuego.findAllByUsuario(usuario);

        model.addAttribute("usuario", usuario);
        model.addAttribute("jugados", listaJugados);
        return "perfil";
    }
    @PostMapping("/editar")
    public String editar(@Valid @ModelAttribute("usuario") User usuario, BindingResult result,
                         @RequestParam("file")MultipartFile file){
        if(result.hasErrors()){
            return "perfil";
        }else {
            if (!file.isEmpty()) {
                String extension= file.getOriginalFilename();
                extension=extension.substring(extension.lastIndexOf(".") + 1);
                String imagen = storageService.store(file,usuario.getId().toString() + "." + extension);
                System.out.println("La imagen a guardar es : " + imagen);
                usuario.setAvatar(MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
            }
        }
        //Quitar espacios al final
        usuario.setName(usuario.getName().trim());

        //Modificar usuario
        servicioUsuario.save(usuario);
        return "redirect:/perfil";
    }

    @GetMapping("/password")
    public String editarPassword(Model model, Authentication authentication){

        User usuario = servicioUsuario.findByEmail(authentication.getName());
        model.addAttribute("usuario", usuario);

        return "cambiarPassword";
    }
    @PostMapping("/password/change")
    public String cambioPassword(@Valid @ModelAttribute("usuario") User usuario, @RequestParam("password_actual") String passActual,
                                 @RequestParam("password_nueva") String passNueva, @RequestParam("password_confirm") String passConfirm,
                                 BindingResult bindingResult,Model model){
        //Comprobacion de password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(passwordEncoder.matches(passActual, usuario.getPassword())){
            if(passNueva.equals(passConfirm)){
                usuario.setPassword(passwordEncoder.encode(passNueva));
            }
        }else{
            bindingResult.rejectValue("password", "Contraseña incorrecta");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("usuario", usuario);
            return "cambiarPassword";
        }

        servicioUsuario.save(usuario);
        return "redirect:/";
    }
}
