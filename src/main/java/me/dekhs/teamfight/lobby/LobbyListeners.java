package me.dekhs.teamfight.lobby;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.managers.TeamManager;
import me.dekhs.teamfight.game.state.GameState;
import me.dekhs.teamfight.game.tasks.GameTasks;
import me.dekhs.teamfight.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyListeners implements Listener {


    private GameState state = Teamfight.getINSTANCE().getGameState();
    private TeamManager teamManager = Teamfight.getINSTANCE().getTeamManager();

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
            // La partie va commencer
            // todo serveur indisponible infra bungeecord
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
        Teamfight.getINSTANCE().getGameManager().giveItemsLobby(player);
        if(Bukkit.getOnlinePlayers().size() == size) {
            state = GameState.STARTING;
            Bukkit.getScheduler().runTaskTimer(Teamfight.getINSTANCE(), new GameTasks(), 0, 20);
            //TODO enclencher début de la partie avec cooldown etc ...
        }

    }

    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack is = event.getItem();
        Action action = event.getAction();
        if(is == null || action == Action.PHYSICAL || is.getType() == Material.AIR) {
            return;
        }
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if (is.getItemMeta().getDisplayName().contains("blanche") && is.getType() == Material.WOOL) {
                teamManager.setWhitePlayers(player);
                player.sendMessage("Tu as rejoint l'équipe blanche");
                //TODO faire a terme une gui pour rejoindre une equipe avec un lore qui supdate en fonction de la liste des teams come sur funcraft
            }
            if (is.getItemMeta().getDisplayName().contains("noir") && is.getType() == Material.WOOL) {
                teamManager.setBlackPlayers(player);
                player.sendMessage("Tu as rejoint l'équipe noir");
                //TODO faire a terme une gui pour rejoindre une equipe avec un lore qui supdate en fonction de la liste des teams come sur funcraft
            }
        }
    }

}
