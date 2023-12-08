package me.cable.deathswap;

import me.cable.deathswap.command.MainCommand;
import me.cable.deathswap.timer.SwapTimer;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathSwap extends JavaPlugin {

    public static boolean running = false;

    @Override
    public void onEnable() {
        new MainCommand(this).register("deathswap");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new SwapTimer(), 0, 20);
    }
}
