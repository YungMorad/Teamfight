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
        ItemStack whiteTeam = new ItemBuilder(Material.WOOD, 1).setName("Rejoindre l'équipe blanche").toItemStack();
        ItemStack blackTeam = new ItemBuilder(Material.WOOD, 1, (byte) 15).setName("Rejoindre l'équipe noire").toItemStack();
        ItemStack lobbyItem = new ItemBuilder(Material.COMPASS, 1).setName("Retourner au lobby").toItemStack();

        player.getInventory().clear();
        player.updateInventory();
        player.getInventory().setItem(0, whiteTeam);
        player.getInventory().setItem(1, blackTeam);
        player.getInventory().setItem(8, lobbyItem);
    }

    public void newGame(List<Player> players){

        Game game = new Game(Teamfight.getINSTANCE().getTeamWhite(), Teamfight.getINSTANCE().getTeamBlack(), players);

        List<Player> whiteTeamPlayers = Teamfight.getINSTANCE().getTeamWhite().getPlayers();
        List<Player> blackTeamPlayers = Teamfight.getINSTANCE().getTeamBlack().getPlayers();

    }

}
