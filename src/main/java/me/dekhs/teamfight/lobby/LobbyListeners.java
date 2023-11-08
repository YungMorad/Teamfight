package me.dekhs.teamfight.lobby;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.state.GameState;
import me.dekhs.teamfight.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyListeners implements Listener {


    private GameState state = Teamfight.getINSTANCE().getGameState();

    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(state == GameState.PLAYING || state == GameState.FINISHING) {
            event.setJoinMessage(null);
            PlayerUtils.resetPlayer(player);
            player.setGameMode(GameMode.SPECTATOR);
            // La partie est déja en cours, le mettre donc en spec
            return;
        }
        if (state == GameState.STARTING) {
            player.kickPlayer("Le jeu vient de se commencer !");
            return;
            //L La partie va commencer
        }
        // La partie na pas comencé
        int size;
        switch (Teamfight.getINSTANCE().getConfig().getString("size")) {
            case "5v5":
                size = 10;
                break;
            case "5V5":
                size = 10;
                break;
            case "4v4":
                size = 8;
                break;
            case "4V4":
                size = 8;
                break;
            case "3v3":
                size = 6;
                break;
            case "3V3":
                size = 6;
                break;
            case "2v2":
                size = 4;
                break;
            case "2V2":
                size = 4;
                break;
            case "1v1":
                size = 2;
                break;
            case "1V1":
                size = 2;
                break;
            default:
                size = 5;
        }
        event.setJoinMessage("Le joueur : " + player.getDisplayName() + " a rejoin la partie ! [" + Bukkit.getOnlinePlayers().size() + "/" + size);
        if(Bukkit.getOnlinePlayers().size() == size) {
            state = GameState.STARTING;
            //TODO enclencher début de la partie avec cooldown etc ...s
        }

    }
}
