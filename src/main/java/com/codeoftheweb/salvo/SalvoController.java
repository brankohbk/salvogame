package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
//al agregar en la URL /api, corre los metodos de este controlador.
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private  GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ShipRepository shipRepository;

    String currentUser="";


    //*************************** LISTAR JUEGOS EXISTENTES ***************************
    @RequestMapping("/games")
    public Map<String, Object> makeGamesList(Authentication authentication){
        Map<String,Object> dto = new LinkedHashMap<>();

        if (isGuest(authentication)){
            dto.put("player","Guest");
        }else{
            currentUser = authentication.getName();
            Player player = playerRepository.findByUserName(currentUser);
            dto.put("player",player.PlayerDTO());
        }

        dto.put("games", gameRepository.findAll()
                .stream()
                .map(Game -> Game.makeGameDTO())
                .collect(Collectors.toList()));
        return dto;
    }

    //*************************** CREAR UN JUEGO NUEVO ***************************
    @RequestMapping(path= "/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createGame(Authentication authentication){
        if(isGuest(authentication)){
            return new ResponseEntity<>(makeMap("error","You must login!"), HttpStatus.FORBIDDEN);
        }else{
            Date now= new Date();
            Game game = new Game(now);
            gameRepository.save(game);
            Player player = playerRepository.findByUserName(authentication.getName());
            GamePlayer gPlayer = new GamePlayer(now,player,game);
            gamePlayerRepository.save(gPlayer);
            return new ResponseEntity<>(makeMap("gpid", gPlayer.getId()), HttpStatus.CREATED);
        }
    }

    //*************************** INGRESAR A UN JUEGO CREADO ***************************
    @RequestMapping(path="/games/{id}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> JoinGame(Authentication authentication,
                                                       @PathVariable long id){
        if (isGuest(authentication)){
            return  new ResponseEntity<>(makeMap("error","No player logged in."),HttpStatus.UNAUTHORIZED);
        }
        Game game =gameRepository.findById(id).orElse(null);
        if (game==null){
            return new ResponseEntity<>(makeMap("error","No such game."),HttpStatus.CONFLICT);
        }
        if (game.getGamePlayers().stream().map(gp -> gp.getPlayer().getUserName()).collect(Collectors.toList()).contains(authentication.getName())){
            return new ResponseEntity<>(makeMap("error","User already in game"), HttpStatus.FORBIDDEN);
        }
        if (game.getGamePlayers().size()==2){
            return new ResponseEntity<>(makeMap("error","Game is full."),HttpStatus.FORBIDDEN);
        }

        Date now= new Date();
        Player player = playerRepository.findByUserName(authentication.getName());

        GamePlayer gp = new GamePlayer(now,player,game);
        gamePlayerRepository.save(gp);

        return new ResponseEntity<>(makeMap("gpid", gp.getId()),HttpStatus.CREATED);
    }

    //*************************** TRAE LOS JUGADORES DE UN DETERMINADO JUEGO***************************
    @RequestMapping(path="/games/{id}/players")
    public Map<String,Object> PlayersInGame(@PathVariable long id){
        Game game =gameRepository.findById(id).orElse(null);

        Map<String,Object> dto = new LinkedHashMap<String, Object>();

        dto.put("players", game.getGamePlayersList(game.getGamePlayers()));
        return dto;

    }

    //*************************** TRAE PUNTAJE Y ARMA LEADERBOARD ***************************
    @RequestMapping("/leaderboard")
    public Map<String, Object> makeLeaderboard(){


        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("players", playerRepository.findAll()
                        .stream()
                        .sorted(Comparator.comparingDouble(Player::getTotalScore).reversed())
                        .sorted(Comparator.comparingDouble(Player::getWins).reversed())
                        .map(player -> player.makePlayerScoreDTO())
                .collect(Collectors.toList())
                );

        return dto;
    }

    //*************************** TRAER POSICIONES DE BARCOS Y SALVOS ***************************
    @RequestMapping("/game_view/{nn}")
    public Map<String,Object> getGameViewByGamePlayerId(@PathVariable Long nn){
        GamePlayer gamePlayer = gamePlayerRepository.findById(nn).get();

        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created",gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
                        .stream()
                        .sorted(Comparator.comparingLong(GamePlayer::getId))
                        .map(gamePlayer1 -> gamePlayer1.makeGamePlayerDTO())
                        .collect(Collectors.toList())
                );
        dto.put("ships",
                gamePlayer.getShips()
                        .stream()
                        .sorted(Comparator.comparingLong(Ship::getId))
                        .map(ship -> ship.ShipDTO())
                        .collect(Collectors.toList())
                );

        dto.put("salvoes",
                gamePlayer.getGame()
                        .getGamePlayers()
                        .stream()
                        .flatMap(gp -> gp.getSalvoes()
                                .stream()
                                .sorted(Comparator.comparingLong(Salvo::getId))
                                .map(salvo ->  salvo.SalvoDTO())
                                )
                                .collect(Collectors.toList())
        );

        return dto;
    }

    //*************************** CREAR JUGADOR (SIGNUP) ***************************
    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam String user, @RequestParam String pass) {
        if (user.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", "Please insert an email"), HttpStatus.FORBIDDEN);
        }
        if(pass.isEmpty()){
            return new ResponseEntity<>(makeMap("error","Please, fill the password field"), HttpStatus.FORBIDDEN);
        }
        Player player = playerRepository.findByUserName(user);
        if (player != null) {
            return new ResponseEntity<>(makeMap("error", "Username already exists"), HttpStatus.CONFLICT);
        }
        Player newPlayer = playerRepository.save(new Player(user, passwordEncoder().encode(pass)));
        return new ResponseEntity<>(makeMap("Player created successfully. ID", newPlayer.getId()), HttpStatus.CREATED);
    }



    //*************************** FUNCIONES AUXILIARES ***************************
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }





}

