package dev.sebastianb.picklockchest.listener;

import dev.sebastianb.picklockchest.Constants;
import dev.sebastianb.picklockchest.PickLockChest;
import dev.sebastianb.picklockchest.commands.locker.PlayerLockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractorListener implements Listener {

    private PickLockChest plugin;


    public PlayerInteractorListener(PickLockChest plugin) {
        this.plugin = plugin;
    }





    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getHand() == EquipmentSlot.HAND)) return;
        handleBlockInteract(event, player, event.getClickedBlock());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!PlayerLockData.getPlayerLockStatus(player)) {
            if (event.getBlock().getType() == Material.CHEST) {
                event.setCancelled(true); // TODO: Take care of all other container types
            }
        }
    }


    private void handleBlockInteract(Cancellable event, Player player, Block clickedBlock) {
        if (!PlayerLockData.getPlayerLockStatus(player)) {
            if (clickedBlock.getType() == Material.CHEST) {
                return; // TODO: Take care of all other container types
            }


            player.sendMessage(Constants.pluginName + "Disable lock mode to do this!");
            event.setCancelled(true);

        }
    }

}
