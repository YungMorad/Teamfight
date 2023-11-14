package me.dekhs.teamfight.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CheckUtilsListener implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onLeave(PlayerQuitEvent event) {
        Bukkit.getOnlinePlayers().remove(event.getPlayer());
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onKick(PlayerKickEvent event) {
        Bukkit.getOnlinePlayers().remove(event.getPlayer());
    }

}
