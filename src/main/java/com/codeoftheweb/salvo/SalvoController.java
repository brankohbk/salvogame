package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping("/games")
    public List<Map<String,Object>> getGames(){
        return gameRepository.findAll()
                .stream()
                .map(Game -> Game.makeGameDTO())
                .collect(Collectors.toList());
    }

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

