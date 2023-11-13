package me.dekhs.teamfight.game.managers;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.Game;
import me.dekhs.teamfight.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GameManager {

    public void giveItemsLobby(Player player) {
        ItemStack chooseTeam = new ItemBuilder(Material.COMPASS, 1).setName("Rejoindre une équipe").toItemStack();
        ItemStack randomTeam = new ItemBuilder(Material.WOOL, 1).setName("Rejoindre aucune équipe").toItemStack();
        ItemStack lobbyItem = new ItemBuilder(Material.WOOD_DOOR, 1).setName("Retourner au lobby").toItemStack();

        player.getInventory().clear();
        player.updateInventory();
        player.getInventory().setItem(0, chooseTeam);
        player.getInventory().setItem(3, randomTeam);
        player.getInventory().setItem(8, lobbyItem);
    }

    public void newGame(List<Player> players){

        Game game = new Game(Teamfight.getINSTANCE().getTeamWhite(), Teamfight.getINSTANCE().getTeamBlack(), players);

        List<Player> whiteTeamPlayers = Teamfight.getINSTANCE().getTeamWhite().getPlayers();
        List<Player> blackTeamPlayers = Teamfight.getINSTANCE().getTeamBlack().getPlayers();

    }

}
