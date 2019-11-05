package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private int turn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="SalvoLocations")
    private List<String> salvoLocations = new ArrayList<>();


    public Salvo() {
    }

    public Salvo(int turn, GamePlayer gamePlayer, Set<String> salvoLocations) {
        this.turn = turn;
        this.gamePlayer = gamePlayer;
        this.salvoLocations =  new ArrayList<>(salvoLocations);
    }

    public long getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setSalvoLocations(List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }

    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public Map<String, Object> SalvoDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("turn", this.getTurn());
        dto.put("player", this.getGamePlayer().getPlayer().getId());
        dto.put("salvoLocation", this.getSalvoLocations());
        return dto;
    }


}
