package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

@Bean
public CommandLineRunner initData(PlayerRepository playerRepository,
                                  GameRepository gameRepository,
                                  GamePlayerRepository gamePlayerRepository,
                                  ShipRepository shipRepository,
                                  SalvoRepository salvoRepository,
                                  ScoreRepository scoreRepository){
    return (args) ->{
        Player jackBauer = new Player("j.bauer@ctu.gov", passwordEncoder().encode("24") );
        Player chloeOBrian = new Player("c.obrian@ctu.gov", passwordEncoder().encode("42"));
        Player kimBauer = new Player("kim_bauer@gmail.com", passwordEncoder().encode("kb"));
        Player tonyAlmeida = new Player("t.almeida@ctu.gov", passwordEncoder().encode("mole"));
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
        String destroyerType = "destroyer";
        String submarineType ="submarine";
        String patrolType="patrolBoat";
        String battleshipType ="battleShip";
        String aircraftCarrierType="aircraftCarrier";


        //Ship LOCATIONS
        Set<String> l1 = new HashSet<>(Arrays.asList("H2","H3","H4"));
        Set<String> l2 = new HashSet<>(Arrays.asList("E1","F1","G1"));
        Set<String> l3 = new HashSet<>(Arrays.asList("B4","B5"));
        Set<String> l4 = new HashSet<>(Arrays.asList("B5","C5","D5"));
        Set<String> l5 = new HashSet<>(Arrays.asList("F1","F2"));
        Set<String> l6 = new HashSet<>(Arrays.asList("C6","C7"));
        Set<String> l7 = new HashSet<>(Arrays.asList("A2","A3","A4"));
        Set<String> l8 = new HashSet<>(Arrays.asList("G6","H6"));

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
        Ship ship22=new Ship(destroyerType,gp12,l4);
        Ship ship23=new Ship(patrolType,gp11,l6);
        Ship ship24=new Ship(destroyerType,gp13,l4);
        Ship ship25=new Ship(patrolType,gp13,l6);
        Ship ship26=new Ship(submarineType,gp14,l7);
        Ship ship27=new Ship(patrolType,gp14,l8);

        //ADD SHIPS TO DATABASE
        shipRepository.saveAll(Arrays.asList(ship1,ship2,ship3,ship4,ship5,ship6,ship7,ship8,ship9,
                ship10,ship11,ship12,ship13,ship14,ship15,ship16,ship17,ship18,ship19,
                ship20,ship21,ship22,ship23,ship24,ship25,ship26,ship27));


        //SALVO LOCATIONS

        Set<String> sl1 = new HashSet<>(Arrays.asList("B5", "C5", "F1"));
        Set<String> sl2 = new HashSet<>(Arrays.asList("B4", "B5", "B6"));
        Set<String> sl3 = new HashSet<>(Arrays.asList("F2", "D5"));
        Set<String> sl4 = new HashSet<>(Arrays.asList("E1", "H3", "A2"));
        Set<String> sl5 = new HashSet<>(Arrays.asList("A2", "A4", "G6"));
        Set<String> sl6 = new HashSet<>(Arrays.asList("B5", "D5", "C7"));
        Set<String> sl7 = new HashSet<>(Arrays.asList("A3", "H6"));
        Set<String> sl8 = new HashSet<>(Arrays.asList("C5", "C6"));
        Set<String> sl9 = new HashSet<>(Arrays.asList("G6", "H6", "A4"));
        Set<String> sl10= new HashSet<>(Arrays.asList("H1", "H2", "H3"));
        Set<String> sl11 = new HashSet<>(Arrays.asList("A2", "A3", "D8"));
        Set<String> sl12 = new HashSet<>(Arrays.asList("E1", "F2", "G3"));
        Set<String> sl13 = new HashSet<>(Arrays.asList("A3", "A4", "F7"));
        Set<String> sl14 = new HashSet<>(Arrays.asList("B5", "C6", "H1"));
        Set<String> sl15 = new HashSet<>(Arrays.asList("A2", "G6", "H6"));
        Set<String> sl16 = new HashSet<>(Arrays.asList("C5", "C7", "D5"));
        Set<String> sl17 = new HashSet<>(Arrays.asList("A1", "A2", "A3"));
        Set<String> sl18 = new HashSet<>(Arrays.asList("B5", "B6", "C7"));
        Set<String> sl19 = new HashSet<>(Arrays.asList("G6", "G7", "G8"));
        Set<String> sl20 = new HashSet<>(Arrays.asList("C6", "D6", "E6"));
        Set<String> sl21 = new HashSet<>(Arrays.asList("H1", "H8"));

        //SALVO INSTANCES

        Salvo salvo1 = new Salvo(1,gp1,sl1);
        Salvo salvo2 = new Salvo(1,gp2,sl2);
        Salvo salvo3 = new Salvo(2,gp1,sl3);
        Salvo salvo4 = new Salvo(2,gp2,sl4);
        Salvo salvo5 = new Salvo(1,gp3,sl5);
        Salvo salvo6 = new Salvo(1,gp4,sl6);
        Salvo salvo7 = new Salvo(2,gp3,sl7);
        Salvo salvo8 = new Salvo(2,gp4,sl8);
        Salvo salvo9 = new Salvo(1,gp5,sl9);
        Salvo salvo10 = new Salvo(1,gp6,sl10);
        Salvo salvo11 = new Salvo(2,gp5,sl11);
        Salvo salvo12 = new Salvo(2,gp6,sl12);
        Salvo salvo13 = new Salvo(1,gp7,sl13);
        Salvo salvo14 = new Salvo(1,gp8,sl14);
        Salvo salvo15= new Salvo(2,gp7,sl15);
        Salvo salvo16 = new Salvo(2,gp8,sl16);
        Salvo salvo17 = new Salvo(1,gp9,sl17);
        Salvo salvo18 = new Salvo(1,gp10,sl18);
        Salvo salvo19 = new Salvo(2,gp9,sl19);
        Salvo salvo20 = new Salvo(2,gp10,sl20);
        Salvo salvo21 = new Salvo(3,gp10,sl21);

        //ADD SALVOES TO DATABASE
        salvoRepository.saveAll(Arrays.asList(salvo1,salvo2,salvo3,salvo4,salvo5,salvo6,salvo7,salvo8,salvo9,
                salvo10,salvo11,salvo12,salvo13,salvo14,salvo15,salvo16,salvo17,salvo18,salvo19,salvo20,salvo21));


        //CREATE SCORES
        Score score1 =new Score(game1, jackBauer, "w", Date.from(game1.getCreationDate().toInstant().plusSeconds(1800)));
        Score score2 =new Score(game1, chloeOBrian, "l", Date.from(game1.getCreationDate().toInstant().plusSeconds(1800)));
        Score score3 =new Score(game2, jackBauer, "t", Date.from(game2.getCreationDate().toInstant().plusSeconds(1800)));
        Score score4 =new Score(game2, chloeOBrian, "t", Date.from(game2.getCreationDate().toInstant().plusSeconds(1800)));
        Score score5 =new Score(game3, chloeOBrian, "w", Date.from(game3.getCreationDate().toInstant().plusSeconds(1800)));
        Score score6 =new Score(game3, tonyAlmeida, "l", Date.from(game3.getCreationDate().toInstant().plusSeconds(1800)));
        Score score7 =new Score(game4, chloeOBrian, "t", Date.from(game4.getCreationDate().toInstant().plusSeconds(1800)));
        Score score8 =new Score(game4, jackBauer, "t", Date.from(game4.getCreationDate().toInstant().plusSeconds(1800)));

        //ADD SCORES TO DATABASE
        scoreRepository.saveAll(Arrays.asList(score1,score2,score3,score4,score5,score6,score7,score8));


    };

}


//END OF CLASS
}




@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputUserName -> {
            Player player = playerRepository.findByUserName(inputUserName);
            if (player != null) {
                return new User(player.getUserName(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputUserName);
            }
        });
    }
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/web/games.html").permitAll()
                .antMatchers("/web/**").permitAll()
                .antMatchers("/api/games").permitAll()
                .antMatchers("/api/players").permitAll()
                .antMatchers("/api/game_view/*").hasAuthority("USER")
                .antMatchers("/rest/*").denyAll()
                .antMatchers("/rest/**").denyAll()
                .antMatchers("/**").permitAll();
                http.formLogin()
                .usernameParameter("user")
                .passwordParameter("pass")
                .loginPage("/api/login");

    http.logout().logoutUrl("/api/logout");


    // turn off checking for CSRF tokens
    http.csrf().disable();

    // if user is not authenticated, just send an authentication failure response
    http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if login is successful, just clear the flags asking for authentication
    http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

    // if login fails, just send an authentication failure response
    http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if logout is successful, just send a success response
    http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
  }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}