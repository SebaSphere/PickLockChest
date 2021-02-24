package dev.sebastianb.picklockchest.listener;

import dev.sebastianb.picklockchest.Constants;
import dev.sebastianb.picklockchest.PickLockChest;
import dev.sebastianb.picklockchest.commands.locker.PlayerLockData;
import dev.sebastianb.picklockchest.data.LockableContainer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestInteractionListener implements Listener {

    private PickLockChest plugin;

    public ChestInteractionListener(PickLockChest plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChestClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        LockableContainer container = new LockableContainer(event.getClickedBlock());
        preventContainerUse(event, Material.CHEST, container);
        preventContainerUse(event, Material.BARREL, container);

    }




//    @EventHandler
//    public void onContainerOpen(InventoryOpenEvent event) {
////        Player player = (Player) event.getPlayer();
////
////        Container container = null;
////        InventoryHolder inventoryHolder = event.getInventory().getHolder();
////        if (inventoryHolder instanceof Chest) {
////            container = Chest.class.cast(inventoryHolder);
////
////        } else if (inventoryHolder instanceof DoubleChest) {
////            DoubleChest doubleChest = DoubleChest.class.cast(inventoryHolder);
////            container = Chest.class.cast(doubleChest.getLeftSide());
////
////        } else if (inventoryHolder instanceof Container) {
////            container = Container.class.cast(inventoryHolder);
////
////        } else {
////            return;
////        }
////        LockableContainer lockedContainer = new LockableContainer(container);
////        if (lockedContainer.isLocked(container.getLocation(), player)) {
////            event.setCancelled(true);
////        }
//
//    }

    private void preventContainerUse(PlayerInteractEvent event, Material block, LockableContainer container) {
        try {
            if (event.getClickedBlock().getType() == block) {
                if (container.isLocked(event.getClickedBlock().getLocation(), event.getPlayer())) {


                    event.setCancelled(true);
                }
            }
        } catch (NullPointerException e) {
            // hmm some weird error where if I spam click container, throws a null pointer exception
            // TODO: Log this in a log file everytime. Maybe have a config option with diff logging priorities.
            return;
        }
    }
}
