package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
//al agregar en la URL /api, corre los metodos de este controlador.
@RequestMapping("/api")
public class SalvoController {
    @Autowired
   private GameRepository gameRepository;

    //Al
    @RequestMapping("/games")
     public List<Map<String,Object>> getGames(){
        return gameRepository.findAll()
                .stream()
                .map(Game -> makeGameDTO(Game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> makeGameDTO(Game game){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id",game.getId());
        dto.put("created",game.getCreationDate());
        dto.put("gamePlayers", getGamePlayesList(game.getGamePlayers()));
        return dto;
    }
    @RequestMapping("/players")
    public List<Map<String,Object>> getGamePlayesList(Set<GamePlayer> gamePlayers){
        return gamePlayers
                .stream()
                .map(GamePlayer -> makeGamePlayerDTO(GamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String,Object> makeGamePlayerDTO( GamePlayer gamePlayer){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", gamePlayer.getPlayer());
        return dto;
    }
    private Map<String,Object> PlayerDTO (Player player){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", player.getId());
        dto.put("email", player.getUserName());
        return dto;
    }



}
