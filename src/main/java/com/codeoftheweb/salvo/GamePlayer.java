package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.*;


@Entity
public class GamePlayer {

    //Attributes.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date joinDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gamePlayer", fetch= FetchType.EAGER)
    Set<Ship> ships = new LinkedHashSet<>();;

    @OneToMany(mappedBy="gamePlayer", fetch= FetchType.EAGER)
    Set<Salvo> salvoes = new LinkedHashSet<>(); //LinkedHashSet ordena automaticamente los resultados



    //Methods
    public GamePlayer() {
    }

    public GamePlayer(Date joinDate, Player player, Game game) {
        this.joinDate = joinDate;
        this.player = player;
        this.game = game;
    }

    public long getId() {
        return id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    @JsonIgnore
    public Player getPlayer() {
        return player;
    }

    @JsonIgnore
    public Game getGame() {
        return game;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public Set<Salvo> getSalvoes() {
        return salvoes;
    }

    public void setSalvoes(Set<Salvo> salvoes) {
        this.salvoes = salvoes;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public Map<String,Object> makeGamePlayerDTO(){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", this.getId());
        dto.put("player", this.getPlayer().PlayerDTO());
        return dto;
    }




}
