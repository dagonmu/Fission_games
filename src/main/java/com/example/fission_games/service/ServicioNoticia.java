package com.example.fission_games.service;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.entity.User;
import com.example.fission_games.repository.RepositorioNoticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioNoticia {
    @Autowired
    RepositorioNoticia repositorioNoticia;

    public List<Noticia> findAll(){return repositorioNoticia.findAll();}
    public Noticia findById(long id){return repositorioNoticia.findById(id);}
    public List<Noticia> findByPublicador(User publicador){return repositorioNoticia.findByPublicador(publicador);}
    public List<Noticia> findFirst3ByOrderByIdDesc(){return repositorioNoticia.findFirst3ByOrderByIdDesc();}
    public List<Noticia> buscador(String query){
        return repositorioNoticia.findByTituloContainsIgnoreCaseOrContenidoContainsIgnoreCase(query, query);
    }
    public void save(Noticia noticia){repositorioNoticia.save(noticia);}
    public void delete(Noticia noticia){repositorioNoticia.delete(noticia);}
    public void deleteById(long id){repositorioNoticia.deleteById(id);}
}
