package me.cable.deathswap.timer;

import me.cable.deathswap.DeathSwap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwapTimer implements Runnable {

    private static final int COUNTDOWN_START = 5 * 60;
    private static final int COUNTDOWN_SHOW = 10;

    private int countdown = COUNTDOWN_START;

    private void swap(@NotNull Player who, @NotNull Player with, @NotNull Location withLoc) {
        who.sendMessage(ChatColor.RED + "Taking " + ChatColor.GOLD + with.getName() + ChatColor.RED + "'s position.");
        who.teleport(withLoc);
    }

    private void swapPlayers() {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (players.isEmpty()) return;

        Collections.shuffle(players);

        Player firstPlayer = players.get(0);
        Location firstPlayerLocation = firstPlayer.getLocation();

        for (int i = 0; i < players.size() - 1; i++) {
            Player who = players.get(i);
            Player with = players.get(i + 1);
            swap(who, with, with.getLocation());
        }

        swap(players.get(players.size() - 1), firstPlayer, firstPlayerLocation);
    }

    private void sendMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    @Override
    public void run() {
        if (!DeathSwap.running) return;

        countdown--;

        if (countdown == 0) {
            countdown = COUNTDOWN_START;
            swapPlayers();
        } else if (countdown <= COUNTDOWN_SHOW) {
            if (countdown == COUNTDOWN_SHOW) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Swap countdown starting!");
            }

            sendMessage(ChatColor.RED + "Swapping in " + ChatColor.GOLD + countdown
                    + ChatColor.RED + " second" + (countdown == 1 ? "" : "s") + ".");
        }
    }
}
