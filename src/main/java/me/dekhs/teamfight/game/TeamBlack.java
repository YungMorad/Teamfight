package me.dekhs.teamfight.game;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public class TeamBlack {

    @Getter
    public List<Player> players;

    public TeamBlack(List<Player> players) {
        this.players = players;
    }
}
