package me.dekhs.teamfight.game;

import me.dekhs.teamfight.game.state.GameState;
import org.bukkit.entity.Player;

import java.util.List;

public class Game {

    public Team teamA;
    public Team teamB;
    public Team winner;
    public List<Player> gamePlayers;

    public Game(Team teamA, Team teamB, List<Player> gamePlayers) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.winner = null;
        this.gamePlayers = gamePlayers;
    }
}
