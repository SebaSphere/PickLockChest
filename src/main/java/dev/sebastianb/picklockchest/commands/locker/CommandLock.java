package dev.sebastianb.picklockchest.commands.locker;

import dev.sebastianb.picklockchest.commands.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandLock extends AbstractCommand {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can only run this command in-game!");
            return false;
        }
        Player player = (Player) sender;

        if (PlayerLockData.lockModeMap.isEmpty()) {
            PlayerLockData.lockModeMap.put(player, true);
        } else if (!PlayerLockData.lockModeMap.containsKey(player)) {
            PlayerLockData.lockModeMap.put(player, false);
        }

        if (PlayerLockData.lockModeMap.get(player)) {
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 10);
            player.sendTitle(ChatColor.DARK_GREEN + "Lock mode enabled", "Left click to lock a block", 10, 50, 20);
            PlayerLockData.lockModeMap.put(player, false);
        } else if (!PlayerLockData.lockModeMap.get(player)) {
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 10);
            player.sendTitle(ChatColor.RED + "Lock mode disabled", "", 10, 50, 20);
            PlayerLockData.lockModeMap.put(player, true);
        }
        return true;
    }

}
