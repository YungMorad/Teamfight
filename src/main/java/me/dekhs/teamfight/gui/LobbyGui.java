package me.dekhs.teamfight.gui;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.item.ItemDataColor;
import com.samjakob.spigui.menu.SGMenu;
import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyGui {

    public static Teamfight INSTANCE = Teamfight.getINSTANCE();
    public static TeamManager teamManager = INSTANCE.getTeamManager();


    public static void openChooseTeamGui(Player player) {
        // Create a GUI with 3 rows (27 slots)
        SGMenu teamMenu = INSTANCE.getSpiGUI().create("&aSélection des teams", 3);
        List<String> whitePlayerName = new ArrayList<String>();
        for(Player pls : INSTANCE.getTeamWhite().getPlayers()) {
            if(pls == null) {
                return;
            }
            whitePlayerName.add(pls.getDisplayName());
        }

        List<String> blackPlayerName = new ArrayList<String>();
        for(Player pls : INSTANCE.getTeamBlack().getPlayers()) {
            if(pls == null) {
                return;
            }
            blackPlayerName.add(pls.getDisplayName());
        }

        // Create a button
        SGButton whiteTeamButton = new SGButton(
                // Includes an ItemBuilder class with chainable methods to easily
                // create menu items.
                new ItemBuilder(Material.WOOL)
                        .amount(1)
                        .name("Rejoin la team blanche")
                        .color(ItemDataColor.WHITE)
                        .lore(whitePlayerName)
                        .build()
        ).withListener((InventoryClickEvent event) -> {
            // Events are cancelled automatically, unless you turn it off
            // for your plugin or for this inventory.
            teamManager.setWhitePlayers(player);
            event.getWhoClicked().sendMessage("Tu as rejoint l'équipe blanche");
            ((Player) event.getWhoClicked()).updateInventory();
            ItemStack whiteButtons = getItemStackByDisplayName(event.getInventory(), "Rejoin la team blanche");
            whiteButtons.getItemMeta().setLore(whitePlayerName);
            ((Player) event.getWhoClicked()).updateInventory();
            teamMenu.refreshInventory(player);
        });

        // Create a button
        SGButton blackTeamButton = new SGButton(
                // Includes an ItemBuilder class with chainable methods to easily
                // create menu items.
                new ItemBuilder(Material.WOOL)
                        .amount(1)
                        .name("Rejoin la team noir")
                        .color(ItemDataColor.BLACK)
                        .lore(blackPlayerName)
                        .build()
        ).withListener((InventoryClickEvent event) -> {
            // Events are cancelled automatically, unless you turn it off
            // for your plugin or for this inventory.
            teamManager.setWhitePlayers(player);
            event.getWhoClicked().sendMessage("Tu as rejoint l'équipe noir");
            ((Player) event.getWhoClicked()).updateInventory();
            ItemStack blackButtons = getItemStackByDisplayName(event.getInventory(), "Rejoin la team noir");
            blackButtons.getItemMeta().setLore(blackPlayerName);
            ((Player) event.getWhoClicked()).updateInventory();
            teamMenu.refreshInventory(player);


        });


        // Add the button to your GUI
        teamMenu.setButton(12, whiteTeamButton);
        teamMenu.setButton(14, blackTeamButton);


        // Show the GUI
        player.openInventory(teamMenu.getInventory());
    }

    public static ItemStack getItemStackByDisplayName(Inventory inventory, String displayName) {
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
                String itemDisplayName = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
                if (itemDisplayName.contains(displayName)) {
                    return itemStack.clone();
                }
            }
        }
        return null;
    }

}
