package com.example.fission_games.repository;

import com.example.fission_games.entity.User;
import com.example.fission_games.entity.UsuarioVideojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioUsuarioVideojuego extends JpaRepository<UsuarioVideojuego, Long> {
    public List<UsuarioVideojuego> findAllByUsuario(User usuario);
}
