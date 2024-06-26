package me.dekhs.teamfight.game.managers;

import me.dekhs.teamfight.game.GamePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GamePlayerManager {

    public HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public GamePlayer getGamePlayer(Player player) {
        return gamePlayers.get(player);

    }

    public boolean isGamePlayer (Player player) {
        return gamePlayers.containsKey(player);
    }

    public void deletegamePlayer(Player player) {
        if(isGamePlayer(player)) {
            gamePlayers.remove(player, gamePlayers.get(player));
        }
    }

    public void createGamePlayer (Player player) {
        if(!isGamePlayer(player)) {
            GamePlayer gamePlayer = new GamePlayer(player);
            gamePlayers.put(player, gamePlayer);
        }
    }
}
