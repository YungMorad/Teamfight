package me.dekhs.teamfight.lobby;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.GamePlayer;
import me.dekhs.teamfight.game.managers.GamePlayerManager;
import me.dekhs.teamfight.game.managers.TeamManager;
import me.dekhs.teamfight.game.state.GameState;
import me.dekhs.teamfight.game.tasks.GameTasks;
import me.dekhs.teamfight.gui.LobbyGui;
import me.dekhs.teamfight.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyListeners implements Listener {


    private GameState state = Teamfight.getINSTANCE().getGameState();
    private TeamManager teamManager = Teamfight.getINSTANCE().getTeamManager();

    private GamePlayerManager gamePlayerManager = Teamfight.INSTANCE.getGamePlayerManager();

    private GameTasks gameTasks;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(state == GameState.PLAYING || state == GameState.FINISHING) {
            event.setJoinMessage(null);
            PlayerUtils.resetPlayer(player);
            player.setGameMode(GameMode.SPECTATOR);
            // todo le tp aux locs de la game
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
        gamePlayerManager.createGamePlayer(player);
        Teamfight.getINSTANCE().getGameManager().giveItemsLobby(player);
        if(Bukkit.getOnlinePlayers().size() == size) {
            //Début de la partie
            state = GameState.STARTING;
            gameTasks = new GameTasks();
            Bukkit.getScheduler().runTaskTimer(Teamfight.getINSTANCE(), new GameTasks(), 0, 20);
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(state == GameState.STARTING) {
            if (gameTasks != null && !gameTasks.isCancelled()) {
                gameTasks.cancel();
            }
        }
        if(player != null) {
            if(gamePlayerManager.isGamePlayer(player)) {
                gamePlayerManager.deletegamePlayer(player);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack is = event.getItem();
        Action action = event.getAction();
        if(is == null || action == Action.PHYSICAL || is.getType() == Material.AIR) {
            return;
        }
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if (is.getItemMeta().getDisplayName().contains("Rejoindre une équipe") && is.getType() == Material.COMPASS) {
                LobbyGui.openChooseTeamGui(player);
            }
            if (is.getItemMeta().getDisplayName().contains("Rejoindre aucune équipe") && is.getType() == Material.WOOL) {
                teamManager.setNothingTeam(player);
                player.sendMessage("Tu as rejoint aucune équipe");
            }
        }
    }

}
