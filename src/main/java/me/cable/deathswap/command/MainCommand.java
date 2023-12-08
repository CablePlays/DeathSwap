package me.cable.deathswap.command;

import me.cable.deathswap.DeathSwap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class MainCommand implements TabExecutor {

    private final DeathSwap deathSwap;

    public MainCommand(@NotNull DeathSwap deathSwap) {
        this.deathSwap = deathSwap;
    }

    public void register(@NotNull String label) {
        PluginCommand pluginCommand = deathSwap.getCommand(label);

        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        DeathSwap.running = !DeathSwap.running;

        if (DeathSwap.running) {
            sender.sendMessage(ChatColor.GREEN + "Death Swap has been activated.");
        } else {
            sender.sendMessage(ChatColor.GREEN + "Death Swap has been deactivated.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return Collections.emptyList();
    }
}
