package me.dekhs.teamfight.game.tasks;

import me.dekhs.teamfight.Teamfight;
import me.dekhs.teamfight.game.state.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTasks extends BukkitRunnable{

    public Teamfight main = Teamfight.getINSTANCE();
    public GameState state = main.getGameState();
    private int timer = 30;


    @Override
    public void run() {

        for(Player pls : Bukkit.getOnlinePlayers()) {
            pls.setLevel(timer);
        }

        switch(timer) {
            case 30:
                Bukkit.broadcastMessage("Début de la partie dans 30 secondes !");

                break;
            case 5:
                Bukkit.broadcastMessage("Début de la partie dans 5 secondes !");
                for(Player pls : Bukkit.getOnlinePlayers()) {
                    pls.getInventory().clear();
                    pls.updateInventory();
                }
                break;
            case 4:
                Bukkit.broadcastMessage("Début de la partie dans 4 secondes !");
                break;
            case 3:
                Bukkit.broadcastMessage("Début de la partie dans 3 secondes !");
                break;
            case 2:
                Bukkit.broadcastMessage("Début de la partie dans 2 secondes !");
                break;
            case 1:
                Bukkit.broadcastMessage("Début de la partie dans 1 secondes !");
                break;
            default:
                break;
        }

        if(timer == 0) {
            Bukkit.broadcastMessage("La partie a commencé !");
            state = GameState.PLAYING;

            cancel();
        }

        timer --;

    }
}
