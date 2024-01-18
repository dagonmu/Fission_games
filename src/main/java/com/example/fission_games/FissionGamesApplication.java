package com.example.fission_games;

import com.example.fission_games.entity.Noticia;
import com.example.fission_games.entity.Role;
import com.example.fission_games.entity.User;
import com.example.fission_games.entity.Videojuego;
import com.example.fission_games.repository.RoleRepository;
import com.example.fission_games.service.ServicioNoticia;
import com.example.fission_games.service.ServicioVideojuego;
import com.example.fission_games.service.UserService;
import com.example.fission_games.storage.StorageProperties;
import com.example.fission_games.storage.StorageService;
import com.github.javafaker.Faker;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FissionGamesApplication {

	public static void main(String[] args) {
		String command = "C:\\xampp\\mysql\\bin\\mysqld.exe";
		try{
			Process process = Runtime.getRuntime().exec(command);
		}catch (IOException e){
			e.printStackTrace();
		}

		SpringApplication.run(FissionGamesApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ServicioVideojuego servicioVideojuego;

	@Bean
	CommandLineRunner rolesBasicos(RoleRepository roleRepository){
		return args -> {
			Role roleUser = roleRepository.findByName("ROLE_USER");
			if(roleUser==null){
				roleUser = new Role();
				roleUser.setName("ROLE_USER");
				roleRepository.save(roleUser);
				Role roleAdmin=new Role();
				roleAdmin.setName("ROLE_ADMIN");
				roleRepository.save(roleAdmin);
			}
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService servicioUsuarios){
		return args -> {
			if(servicioUsuarios.findAll().size()<1) {
				//La contraseña es 1234
				User usuario=new User("Kallenzar","Daniel", "kallenzar@gmail.com",  "$2a$12$QO8HqfpzA7cUGlyDFQ5/FeKfH.laaMRIFsQiQX8oCVStWX0HavrTW");
				servicioUsuarios.save(usuario);

			}

			//Esto es para añadirle roles a mi usuario si es que no tiene
			User usuario=servicioUsuarios.findByEmail("kallenzar@gmail.com");
			List<Role> listaRoles=usuario.getRoles();
			if(listaRoles.size()==0) {
				Role admin = roleRepository.findByName("ROLE_ADMIN");
				usuario.setRoles(Arrays.asList(admin));
				servicioUsuarios.save(usuario);
			}
		};
	}

	@Bean
	CommandLineRunner creacionDatos(RoleRepository roleRepository){
		return args -> {
			List<Videojuego> listaJuegos = servicioVideojuego.findAll();
			Faker faker = new Faker(new Locale("es-ES"));
			if(listaJuegos.isEmpty()){
				for(int i = 0; i<10;i++) {
					Videojuego videojuego1 = new Videojuego();
					Videojuego videojuego2 = new Videojuego();
					Videojuego videojuego3 = new Videojuego();
					Noticia noticia1 = new Noticia();
					Noticia noticia2 = new Noticia();
					Noticia noticia3 = new Noticia();

					videojuego1.setTitulo(faker.book().title());
					videojuego1.setDescripcion(faker.chuckNorris().fact());
					videojuego1.setGenero("Arcade");
					videojuego1.setEnlace("https://tetris-fission.netlify.app/");
					videojuego1.setPortada("http://localhost:9000/files/Tetris-portada.jpeg");
					videojuego1.setControles("Movimiento: <- | -> ---------- Rotación: F | G");

					videojuego2.setTitulo(faker.book().title());
					videojuego2.setDescripcion(faker.chuckNorris().fact());
					videojuego2.setGenero("Arcade");
					videojuego2.setEnlace("https://snake-fission.netlify.app/");
					videojuego2.setPortada("http://localhost:9000/files/Snake-portada.jpeg");

					videojuego3.setTitulo(faker.book().title());
					videojuego3.setDescripcion(faker.chuckNorris().fact());
					videojuego3.setGenero("Plataformas");
					videojuego3.setEnlace("https://tribal-run.netlify.app/");
					videojuego3.setPortada("http://localhost:9000/files/Tribal Run-portada.png");
					videojuego3.setControles("Movimiento: <- | -> ------ Ataque: Z ---------- Salto: Espacio");

					servicioVideojuego.save(videojuego1);
					servicioVideojuego.save(videojuego2);
					servicioVideojuego.save(videojuego3);
				}
			}
		};
	}
}
