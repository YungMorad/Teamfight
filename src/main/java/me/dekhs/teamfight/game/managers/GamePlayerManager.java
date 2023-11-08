package me.dekhs.teamfight.game.managers;

import me.dekhs.teamfight.game.GamePlayer;
import me.dekhs.teamfight.game.Team;
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
}
