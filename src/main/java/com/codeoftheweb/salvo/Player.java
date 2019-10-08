package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Entity
public class Player {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;

    private String password;

    @OneToMany(mappedBy="player", fetch= FetchType.EAGER)
    Set<GamePlayer> gamePlayers;


    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    Set<Score> scores;

    //Score values

    private float winScore =1;
    private float tieScore =(float) 0.5;
    private float lossScore =0;

    //Methods.
    public Player(){}

    public Player(String userName, String password){
        this.userName=userName;
        this.password=password;
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

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public Map<String,Object> PlayerDTO (){
        Map<String,Object> dto = new LinkedHashMap<String,Object>();
        dto.put("id", this.getId());
        dto.put("name", this.getUserName());
        return dto;
    }
    public float getWins() {
        return this.getScores()
                .stream()
                .filter(score -> (score.getScore().equals("w") || score.getScore().equals("W")))
                .count();
    }

    public float getLosses() {
        return this.getScores()
                .stream()
                .filter(score -> (score.getScore().equals("l") || score.getScore().equals("L")))
                .count();
    }

    public float getDraws() {
        return this.getScores()
                .stream()
                .filter(score -> (score.getScore().equals("t") || score.getScore().equals("T")))
                .count();
    }


    public float getTotalScore() {
        return getWins() * this.winScore + getDraws() * this.tieScore + getLosses() * this.lossScore;
    }
    public String getScore(Game juego){
        Optional<Score> optionalScore= this.getScores()
                .stream()
                .filter(score -> score.getGame().equals(juego))
                .findFirst()
                ;
        if (optionalScore.isEmpty()){
            return null;
        }else{
            return optionalScore.get().getScore();
        }

    }

    public Map<String, Object> makePlayerScoreDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", this.getId());
        dto.put("email", this.getUserName());
        dto.put("total", this.getTotalScore());
        dto.put("wins", this.getWins());
        dto.put("losses", this.getLosses());
        dto.put("draws", this.getDraws());
        return dto;
    }
}
