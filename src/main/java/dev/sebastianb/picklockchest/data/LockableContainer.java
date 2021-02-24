package dev.sebastianb.picklockchest.data;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;

public class LockableContainer {

    private boolean chestClaimed;


    public LockableContainer(Block block) {
        // call all values in data here (a array list of chest loca


    }


    public void unlock(Player player) {

    }

    public void trust(Player player) {

    }

    public void untrust(Player player) {

    }

    public boolean isLocked(Location location) {
        return true;
    }

    public boolean isOwner(Location location, Player player) {
        return true;
    }

    public boolean isTrusted(Location location, Player player) {
        return true;
    }






}
