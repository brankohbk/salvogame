package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date creationDate;

    @OneToMany(mappedBy="game", fetch= FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    public Game(){}

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Map<String, Object> makeGameDTO(){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id",this.getId());
        dto.put("created",this.getCreationDate());
        dto.put("gamePlayers", getGamePlayersList(this.getGamePlayers()));

        return dto;
    }

    public List<Map<String,Object>> getGamePlayersList(Set<GamePlayer> gamePlayers){
        return gamePlayers
                .stream()
                .map(GamePlayer -> GamePlayer.makeGamePlayerDTO())
                .collect(Collectors.toList());
    }



}
