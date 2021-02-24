package dev.sebastianb.picklockchest.listener;

import dev.sebastianb.picklockchest.Constants;
import dev.sebastianb.picklockchest.PickLockChest;
import dev.sebastianb.picklockchest.commands.locker.PlayerLockData;
import dev.sebastianb.picklockchest.data.LockableContainer;
import dev.sebastianb.picklockchest.utils.Utility;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;

import java.util.EnumSet;

public class PlayerInteractorListener implements Listener {

    private PickLockChest plugin;


    public PlayerInteractorListener(PickLockChest plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerLockData.setPlayerLockStatus(event.getPlayer(), false);
    }



    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getHand() == EquipmentSlot.HAND)) return;
        handleBlockInteract(event, player, event.getClickedBlock());

        if (event.getClickedBlock().getType() == Material.CHEST) {
            Chest chest;
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!PlayerLockData.getPlayerLockStatus(player)) {
            if (event.getBlock().getType() == Material.CHEST) {
//                if (!shouldContinue) {
//                    return; // TODO: Take care of all other container types
//                }

            }
        }
    }


    private void handleBlockInteract(Cancellable event, Player player, Block clickedBlock) {
        if (PlayerLockData.getPlayerLockStatus(player)) {
            if (clickedBlock.getType() == Material.CHEST) {
                if (Utility.isDoubleChest(clickedBlock)) {
                    return;
                }


                boolean shouldContinue = handleContainerLock(clickedBlock, player);
                if (!shouldContinue) {
                    return; // TODO: Take care of all other container types
                }

            }


            player.sendMessage(Constants.pluginName + "Disable lock mode to do this!");
            event.setCancelled(true);

        }
    }


    private boolean handleContainerLock(Block block, Player player) {
        LockableContainer container = new LockableContainer(block);
        if (container.isLocked(block.getLocation())) {
            if (container.isOwner(block.getLocation(), player)) {
                return true;
            } else if (container.isTrusted(block.getLocation(), player)){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }


}
