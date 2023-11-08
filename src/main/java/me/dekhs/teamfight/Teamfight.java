package me.dekhs.teamfight;

import lombok.Getter;
import me.dekhs.teamfight.game.managers.GamePlayerManager;
import me.dekhs.teamfight.game.state.GameState;
import me.dekhs.teamfight.lobby.LobbyListeners;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.krb5.Config;

public class Teamfight extends JavaPlugin {

    @Getter
    public static Teamfight INSTANCE;
    @Getter
    public GamePlayerManager gamePlayerManager;
    @Getter
    GameState gameState;
    @Getter
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        loadManagers();
        registerListeners();
        gameState = GameState.WAITING;
    }

    @Override
    public void onLoad() {
        INSTANCE = this;
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new LobbyListeners(), this);
    }

    public void loadManagers() {
        gamePlayerManager = new GamePlayerManager();
    }
}
