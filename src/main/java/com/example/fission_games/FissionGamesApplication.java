package com.example.fission_games;

import com.example.fission_games.entity.Role;
import com.example.fission_games.entity.User;
import com.example.fission_games.repository.RoleRepository;
import com.example.fission_games.service.UserService;
import com.example.fission_games.storage.StorageProperties;
import com.example.fission_games.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
}
