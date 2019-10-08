package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/games")
    public Map<String, Object> makeGamesList(){
        Map<String,Object> dto = new LinkedHashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
             currentUser = authentication.getName();
            Player player = playerRepository.findByUserName(currentUser);
            dto.put("player",player.PlayerDTO());
        }
        else {
            dto.put("player","Guest");
        }
        dto.put("games", gameRepository.findAll()
                .stream()
                .map(Game -> Game.makeGameDTO())
                .collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping("/books")
    public Map<String, Object> getAll(Authentication authentication) {
        return playerRepository.findByUserName(authentication.getName()).PlayerDTO();
    }

  /*  public List<Map<String,Object>> getGames(){
        return gameRepository.findAll()
                .stream()
                .map(Game -> Game.makeGameDTO())
                .collect(Collectors.toList());
    }*/



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





}

