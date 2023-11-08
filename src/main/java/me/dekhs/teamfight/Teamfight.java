package me.dekhs.teamfight;

import lombok.Getter;
import me.dekhs.teamfight.game.TeamBlack;
import me.dekhs.teamfight.game.TeamWhite;
import me.dekhs.teamfight.game.managers.GameManager;
import me.dekhs.teamfight.game.managers.GamePlayerManager;
import me.dekhs.teamfight.game.managers.TeamManager;
import me.dekhs.teamfight.game.state.GameState;
import me.dekhs.teamfight.lobby.LobbyListeners;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import sun.security.krb5.Config;

public class Teamfight extends JavaPlugin {

    @Getter
    public static Teamfight INSTANCE;
    @Getter
    public GamePlayerManager gamePlayerManager;
    @Getter
    public GameManager gameManager;
    @Getter
    public TeamManager teamManager;
    @Getter
    GameState gameState;
    @Getter
    TeamWhite teamWhite;
    @Getter
    TeamBlack teamBlack;
    @Getter
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        gameState = GameState.WAITING;
        teamWhite = new TeamWhite(null);
        teamBlack = new TeamBlack(null);
        loadManagers();
        registerListeners();
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
        gameManager = new GameManager();
        teamManager = new TeamManager();
    }
}
