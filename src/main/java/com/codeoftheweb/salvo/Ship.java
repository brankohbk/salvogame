package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Ship {
    //Attrs

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String shipType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="ShipLocations")
    private List<String> shipLocations = new ArrayList<>();



    //Methods.
    public Ship() {
    }

    public Ship(String shipType, GamePlayer gamePlayer, Set<String> shipLocations) {
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.shipLocations = new ArrayList<>(shipLocations);
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public long getId() {
        return id;
    }

    public List<String> getShipLocations() {
        return shipLocations;
    }

    public void setShipLocations(List<String> shipLocations) {
        this.shipLocations = shipLocations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }


    public Map<String,Object> ShipDTO (){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", this.getId());
        dto.put("Type", this.shipType);
        dto.put("shipLocation",this.shipLocations);
        return dto;
    }

}
