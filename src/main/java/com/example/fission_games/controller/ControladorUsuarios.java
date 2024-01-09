package com.example.fission_games.controller;

import com.example.fission_games.dto.UserDto;
import com.example.fission_games.entity.User;
import com.example.fission_games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/crud")
public class ControladorUsuarios {
    @Autowired
    UserService userService;

    @GetMapping("/usuarios")
    public String listRegisteredUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "crud/listaUsuarios";
    }
}
