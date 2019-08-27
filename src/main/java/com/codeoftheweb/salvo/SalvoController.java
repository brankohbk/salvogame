package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//al agregar en la URL /api, corre los metodos de este controlador.
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;


    @RequestMapping("/games")
     public List<Map<String,Object>> getGames(){
        return gameRepository.findAll()
                .stream()
                .map(Game -> makeGameDTO(Game))
                .collect(Collectors.toList());
    }
    public Map<String, Object> makeGameDTO(Game game){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id",game.getId());
        dto.put("created",game.getCreationDate());
        dto.put("gamePlayers", getGamePlayersList(game.getGamePlayers()));
        return dto;
    }


    @RequestMapping("/players")
    public List<Map<String,Object>> getGamePlayersList(Set<GamePlayer> gamePlayers){
        return gamePlayers
                .stream()
                .map(GamePlayer -> GamePlayer.makeGamePlayerDTO(GamePlayer))
                .collect(Collectors.toList());
    }


   /* @RequestMapping("/game_view/{playerID}")
    public Player findById(@PathVariable Long playerID) {
        Player player = findById(playerID);
        return player;
//        todo VERIFICAR COMO SE ARMA ESTE METODO

    }*/






}
