package dev.sebastianb.picklockchest;

import dev.sebastianb.picklockchest.commands.locker.CommandLock;
import dev.sebastianb.picklockchest.listener.ChestInteractionListener;
import dev.sebastianb.picklockchest.listener.PlayerInteractorListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class PickLockChest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerConfig();
        registerEvents();
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerConfig() {

    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChestInteractionListener(this), this);
        pm.registerEvents(new PlayerInteractorListener(this), this);

    }

    private void registerCommands() {
        this.getCommand("lock").setExecutor(new CommandLock());

    }

}
