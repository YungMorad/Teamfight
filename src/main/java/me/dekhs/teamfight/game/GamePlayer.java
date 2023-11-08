package me.dekhs.teamfight.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter public class GamePlayer {

    public Player player;
    public UUID uuid;
    public int kills;
    public int deaths;
    public Team playerTeam;
    public int hits;

    public GamePlayer(Player player, Team playerTeam) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.kills = 0;
        this.deaths = 0;
        this.playerTeam = playerTeam;
        this.hits = 0;
    }
}
