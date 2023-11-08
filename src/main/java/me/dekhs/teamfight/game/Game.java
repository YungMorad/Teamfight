package me.dekhs.teamfight.game;

import org.bukkit.entity.Player;

import java.util.List;

public class Game {

    public TeamWhite teamA;
    public TeamBlack teamB;
    public List<Player> gamePlayers;

    public Game(TeamWhite teamA, TeamBlack teamB, List<Player> gamePlayers) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.gamePlayers = gamePlayers;
    }
}
