package me.dekhs.teamfight.game;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public class TeamWhite {

    @Getter
    public List<Player> players;

    public TeamWhite(List<Player> players) {
        this.players = players;
    }

}
