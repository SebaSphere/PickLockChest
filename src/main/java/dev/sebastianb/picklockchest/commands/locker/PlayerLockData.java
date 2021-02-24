package dev.sebastianb.picklockchest.commands.locker;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerLockData {

    protected static final HashMap<Player, Boolean> lockModeMap = new HashMap<>();

    public static boolean getPlayerLockStatus(Player player) {
        try {
            return lockModeMap.get(player);
        } catch (NullPointerException e) {
            // if player hasn't run command yet, return false
            return false;
        }
    }

    public static void setPlayerLockStatus(Player player, boolean lockStatus) {
        lockModeMap.put(player, lockStatus);
    }

}
