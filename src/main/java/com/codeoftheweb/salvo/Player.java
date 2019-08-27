package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;

    @OneToMany(mappedBy="player", fetch= FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    //Methods.
    public Player(){}

    public Player(String userName){
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String,Object> PlayerDTO (Player player){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", player.getId());
        dto.put("email", player.getUserName());
        return dto;
    }
}
