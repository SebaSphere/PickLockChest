package dev.sebastianb.picklockchest.listener;

import dev.sebastianb.picklockchest.Constants;
import dev.sebastianb.picklockchest.PickLockChest;
import dev.sebastianb.picklockchest.commands.locker.PlayerLockData;
import dev.sebastianb.picklockchest.data.LockableContainer;
import dev.sebastianb.picklockchest.utils.Utility;
import org.bukkit.Location;
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




    private void preventContainerUse(PlayerInteractEvent event, Material block, LockableContainer container) {
        try {
            if (event.getClickedBlock().getType() == block) {
                Location location = event.getClickedBlock().getLocation();
                Player player = event.getPlayer();

                System.out.println("_______");
                System.out.println("old: " + event.getClickedBlock());
                System.out.println(Utility.getLeftDoubleChestLocation(event.getClickedBlock()));


                if (PlayerLockData.getPlayerLockStatus(player)) {
                    return;
                }

                if (container.isTrusted(location, player)) {
                    return;
                }

                if (!container.isLocked(location)) {
                    player.sendMessage("not locked");
                    return;
                } else {
                    if (container.isOwner(location, player)) {
                        player.sendMessage("locked but have permision to use");
                        return;
                    }
                    player.sendMessage("locked");
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
