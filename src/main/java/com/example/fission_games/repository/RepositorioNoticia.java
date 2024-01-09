package com.example.fission_games.repository;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioNoticia extends JpaRepository<Noticia, Long> {
    public Noticia findById(long id);
    public List<Noticia> findByPublicador(User publicador);
    public List<Noticia> findFirst3ByOrderByIdDesc();
    public List<Noticia> findByTituloContainsIgnoreCaseOrContenidoContainsIgnoreCase(String titulo, String contenido);
    public void deleteById(long id);
}
