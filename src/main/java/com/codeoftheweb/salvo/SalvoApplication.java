package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}



@Bean
public CommandLineRunner initData(PlayerRepository playerRepository,
                                  GameRepository gameRepository){
    return (args) ->{
        playerRepository.save(new Player("jackbauer@mail.net"));
        playerRepository.save(new Player("cobrian@mail.net"));
        playerRepository.save(new Player("kbauer@mail.net"));
        playerRepository.save(new Player("davidpalmer@mail.net"));
        playerRepository.save(new Player("mdessler@mail.net"));
        Date now= new Date();
        Game game1= new Game(now);
        Game game2= new Game(Date.from(now.toInstant().plusSeconds(3600)));
        Game game3= new Game(Date.from(now.toInstant().plusSeconds(7200)));
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);

    };

}


//END OF CLASS
}