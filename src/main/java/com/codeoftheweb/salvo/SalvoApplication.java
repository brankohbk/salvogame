package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}



@Bean
public CommandLineRunner initData(PlayerRepository repository){
    return (args) ->{
        repository.save(new Player("jackbauer@mail.net"));
        repository.save(new Player("cobrian@mail.net"));
        repository.save(new Player("kbauer@mail.net"));
        repository.save(new Player("davidpalmer@mail.net"));
        repository.save(new Player("mdessler@mail.net"));
        System.out.println("User name:"+repository.findByUserName("kbauer@mail.net").get(0).getUserName());
        System.out.println(" // ID:"+repository.findByUserName("kbauer@mail.net").get(0).getId());

    };
}

}