package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}



@Bean
public CommandLineRunner initData(PlayerRepository playerRepository,
                                  GameRepository gameRepository,
                                  GamePlayerRepository gamePlayerRepository,
                                  ShipRepository shipRepository){
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
         GamePlayer gp4 = new GamePlayer(time2,chloeOBrian, game2);
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

        //SHIPS
        //Ship TYPES
        String destroyerType = "Destroyer";
        String submarineType ="Submarine";
        String patrolType="Patrol Boat";

        //Ship LOCATIONS
        Set<String> l1 = new HashSet<>(Arrays.asList("h2","h3","h4"));
        Set<String> l2 = new HashSet<>(Arrays.asList("e1","f1","g1"));
        Set<String> l3 = new HashSet<>(Arrays.asList("b4","b5"));
        Set<String> l4 = new HashSet<>(Arrays.asList("b5","c5","d5"));
        Set<String> l5 = new HashSet<>(Arrays.asList("f1","f2"));
        Set<String> l6 = new HashSet<>(Arrays.asList("c6","c7"));
        Set<String> l7 = new HashSet<>(Arrays.asList("a2","a3","a4"));
        Set<String> l8 = new HashSet<>(Arrays.asList("g6","h6"));

        //SHIP INSTANCES
        Ship ship1 = new Ship(destroyerType,gp1,l1);
        Ship ship2 = new Ship(submarineType,gp1,l2);
        Ship ship3 = new Ship(patrolType,gp1,l3);
        Ship ship4 = new Ship(destroyerType,gp2,l4);
        Ship ship5 = new Ship(patrolType,gp2,l5);
        Ship ship6 = new Ship(destroyerType,gp3,l4);
        Ship ship7 = new Ship(patrolType,gp3,l6);
        Ship ship8 = new Ship(submarineType,gp4,l7);
        Ship ship9 = new Ship(patrolType,gp4,l8);
        Ship ship10=new Ship(destroyerType,gp5,l4);
        Ship ship11=new Ship(patrolType,gp5,l6);
        Ship ship12=new Ship(submarineType,gp6,l7);
        Ship ship13=new Ship(patrolType,gp6,l8);
        Ship ship14=new Ship(destroyerType,gp7,l4);
        Ship ship15=new Ship(patrolType,gp7,l6);
        Ship ship16=new Ship(submarineType,gp8,l7);
        Ship ship17=new Ship(patrolType,gp8,l8);
        Ship ship18=new Ship(destroyerType,gp9,l4);
        Ship ship19=new Ship(patrolType,gp9,l6);
        Ship ship20=new Ship(submarineType,gp10,l7);
        Ship ship21=new Ship(patrolType,gp10,l8);
        Ship ship22=new Ship(destroyerType,gp11,l4);
        Ship ship23=new Ship(patrolType,gp11,l6);
        Ship ship24=new Ship(destroyerType,gp13,l4);
        Ship ship25=new Ship(patrolType,gp13,l6);
        Ship ship26=new Ship(submarineType,gp14,l7);
        Ship ship27=new Ship(patrolType,gp14,l8);

        //ADD SHIPS TO DATABASE
        shipRepository.saveAll(Arrays.asList(ship1,ship2,ship3,ship4,ship5,ship6,ship7,ship8,ship9,
                ship10,ship11,ship12,ship13,ship14,ship15,ship16,ship17,ship18,ship19,
                ship20,ship21,ship22,ship23,ship24,ship25,ship26,ship27));








    };

}


//END OF CLASS
}