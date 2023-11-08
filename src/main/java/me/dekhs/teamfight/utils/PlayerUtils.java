package me.dekhs.teamfight.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void resetPlayer(Player player) {
        player.setFoodLevel(20);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setHealth(20);
    }

}
