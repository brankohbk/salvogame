package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}



@Bean
public CommandLineRunner initData(PlayerRepository playerRepository,
                                  GameRepository gameRepository,
                                  GamePlayerRepository gamePlayerRepository){
    return (args) ->{
        Player jackBauer = new Player("jbauer@ctu.gov");
        Player chloeOBrian = new Player("c.obrian@ctu.gov");
        Player kimBauer = new Player("kim_bauer@gmail.com");
        Player tonyAlmeida = new Player("t.almeida@ctu.gov");
        playerRepository.save(jackBauer);
        playerRepository.save(chloeOBrian);
        playerRepository.save(kimBauer);
        playerRepository.save(tonyAlmeida);
        Date now= new Date();
        Date time2 = Date.from(now.toInstant().plusSeconds(3600));
        Date time3 = Date.from(time2.toInstant().plusSeconds(3600));
        Date time4 = Date.from(time3.toInstant().plusSeconds(3600));
        Date time5 = Date.from(time4.toInstant().plusSeconds(3600));
        Date time6 = Date.from(time5.toInstant().plusSeconds(3600));
        Date time7 = Date.from(time6.toInstant().plusSeconds(3600));
        Date time8 = Date.from(time7.toInstant().plusSeconds(3600));
        Game game1= new Game(now);
        Game game2= new Game(time2);
        Game game3= new Game(time3);
        Game game4= new Game(time4);
        Game game5= new Game(time5);
        Game game6= new Game(time6);
        Game game7= new Game(time7);
        Game game8= new Game(time8);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        gameRepository.save(game5);
        gameRepository.save(game6);
        gameRepository.save(game7);
        gameRepository.save(game8);


         GamePlayer gp1 = new GamePlayer(now,jackBauer, game1);
         GamePlayer gp2 = new GamePlayer(now,chloeOBrian, game1);
         GamePlayer gp3 = new GamePlayer(time2,jackBauer, game2);
         GamePlayer gp4 = new GamePlayer(time2,jackBauer, game2);
         GamePlayer gp5 = new GamePlayer(time3,chloeOBrian, game3);
         GamePlayer gp6 = new GamePlayer(time3,tonyAlmeida, game3);
         GamePlayer gp7 = new GamePlayer(time4,chloeOBrian, game4);
         GamePlayer gp8 = new GamePlayer(time4,jackBauer, game4);
         GamePlayer gp9 = new GamePlayer(time5,tonyAlmeida, game5);
         GamePlayer gp10 = new GamePlayer(time5,jackBauer, game5);
         GamePlayer gp11 = new GamePlayer(time6,kimBauer, game6);
         GamePlayer gp12 = new GamePlayer(time7,tonyAlmeida, game7);
         GamePlayer gp13 = new GamePlayer(time8,kimBauer, game8);
         GamePlayer gp14 = new GamePlayer(time8,tonyAlmeida, game8);



        gamePlayerRepository.save(gp1);
        gamePlayerRepository.save(gp2);
        gamePlayerRepository.save(gp3);
        gamePlayerRepository.save(gp4);
        gamePlayerRepository.save(gp5);
        gamePlayerRepository.save(gp6);
        gamePlayerRepository.save(gp7);
        gamePlayerRepository.save(gp8);
        gamePlayerRepository.save(gp9);
        gamePlayerRepository.save(gp10);
        gamePlayerRepository.save(gp11);
        gamePlayerRepository.save(gp12);
        gamePlayerRepository.save(gp13);
        gamePlayerRepository.save(gp14);

        List<String> locs1 = <> ;

        Ship ship1 = new Ship("Boat", gp1,locs1);



    };

}


//END OF CLASS
}