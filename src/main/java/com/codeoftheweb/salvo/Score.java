package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerId")
    private Player player;

    private String score;

    private Date finishDate;

    //*********** METHODS
    public Score() {
    }

    public Score(Game game, Player player, String score, Date finishDate) {
        this.game = game;
        this.player = player;
        this.score = score;
        this.finishDate = finishDate;
    }

    public long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public String getScore() {
        return score;
    }

    public Date getFinishDate() {
        return finishDate;
    }


    //************* DTO
    public Map<String, Object> scoreDTO(){
        Map<String,Object> dto = new LinkedHashMap<String, Object>();
        dto.put("playerID",this.getPlayer().getId());
        dto.put("score",this.getScore());
        dto.put("finishDate", this.getFinishDate());
        return dto;
    }

}
