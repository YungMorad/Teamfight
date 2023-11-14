package me.dekhs.teamfight.game.managers;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.GamePlayer;
import me.dekhs.teamfight.game.TeamBlack;
import me.dekhs.teamfight.game.TeamWhite;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    public HashMap<Player, TeamWhite> whitePlayers = new HashMap<>();
    public HashMap<Player, TeamBlack> blackPlayers = new HashMap<>();

    public TeamWhite teamWhite = Teamfight.getINSTANCE().getTeamWhite();
    public TeamBlack teamBlacks = Teamfight.getINSTANCE().getTeamBlack();

    public GamePlayerManager gamePlayerManager = Teamfight.getINSTANCE().getGamePlayerManager();




    public TeamWhite getWhiteTeamPlayer(Player player) {
        return whitePlayers.get(player);

    }

    public TeamBlack getBlackTeamPlayer(Player player) {
        return blackPlayers.get(player);

    }

    public void resetPlayer(Player player) {
        setNothingTeam(player);
        if(gamePlayerManager.isGamePlayer(player)) {
            gamePlayerManager.deletegamePlayer(player);
        }
    }

    public void setNothingTeam (Player player) {
        if(isWhiteTeam(player)) {
            whitePlayers.remove(player, teamWhite);
            teamWhite.getPlayers().remove(player);
        }
        if(isBlackTeam(player)) {
            blackPlayers.remove(player, teamBlacks);
            teamBlacks.getPlayers().remove(player);
        }
    }

    public boolean isWhiteTeam (Player player) {
        return whitePlayers.containsKey(player);
    }

    public boolean isBlackTeam (Player player) {
        return blackPlayers.containsKey(player);
    }

    public void setWhitePlayers (Player player) {
        if(isBlackTeam(player) || isWhiteTeam(player)) {
            return;
        }
        whitePlayers.put(player, teamWhite);
        teamWhite.getPlayers().add(player);
        gamePlayerManager.getGamePlayer(player).setTeamWhite(teamWhite);
    }

    public void setBlackPlayers (Player player) {
        if(isWhiteTeam(player) || isBlackTeam(player)) {
            return;
        }
        blackPlayers.put(player, teamBlacks);
        teamWhite.getPlayers().add(player);
        gamePlayerManager.getGamePlayer(player).setTeamBlack(teamBlacks);
    }



}
