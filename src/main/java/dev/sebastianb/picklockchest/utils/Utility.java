package dev.sebastianb.picklockchest.utils;

import dev.sebastianb.picklockchest.commands.exceptions.NoChestException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.data.type.Chest;
import org.bukkit.inventory.InventoryHolder;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {


    private static final DecimalFormat decimalFormat = new DecimalFormat("#");
    private static final EnumSet<Material> chestTypes = EnumSet.of(Material.CHEST, Material.TRAPPED_CHEST);

    public static boolean isDoubleChest(Block block) {
        if (!chestTypes.contains(block.getType())) {
            return false;
        }
        Chest chest = (org.bukkit.block.data.type.Chest) block.getBlockData();

        return chest.getType() != Chest.Type.SINGLE;
    }


    // I fucking HATE how spigot does this. Grabs Chest.Type from back, so inverse from a normal view
    // Chest.Type.RIGHT == left side of chest. That's at least what I'm seeing
    public static Block getLeftDoubleChestLocation(Block block) {
        if (!chestTypes.contains(block.getType())) {
            throw new NoChestException(block.toString() + " has been inputted. Not a chest.");
        }
        if (!isDoubleChest(block)) {
            return block;
        } else {
            Chest chest = (Chest) block.getBlockData();
            org.bukkit.block.Chest chestBlock = (org.bukkit.block.Chest) block.getState();
            System.out.println(chest.getType());
            if (chest.getType() == Chest.Type.SINGLE) {
                return block;
            }
            if (chest.getType() == Chest.Type.LEFT) {
                InventoryHolder holder = chestBlock.getInventory().getHolder();
                if (holder instanceof DoubleChest) {
                    System.out.println("right");
                    //System.out.println("right");
                    DoubleChest doubleChest = ((DoubleChest) holder);
                    Location leftSide = doubleChest.getRightSide().getInventory().getLocation();
                    return leftSide.getBlock();

                }
            }
            if (chest.getType() == Chest.Type.RIGHT) {
                InventoryHolder holder = chestBlock.getInventory().getHolder();
                if (holder instanceof DoubleChest) {
                    System.out.println("left");
                    DoubleChest doubleChest = ((DoubleChest) holder);
                    Location leftSide = doubleChest.getRightSide().getInventory().getLocation();
                    return leftSide.getBlock(); // so depending on the direction, returns a diff type what??

                }
            }

            System.out.println("shouldn't happpen");
            return block; // don't think this should happen
        }
    }

    private static final String regex = "([^.]*)";


    private static final Pattern pattern = Pattern.compile(regex);

//    private static int getCoord(double number) {
//        String string = String.valueOf(number);
//
//        return Integer.parseInt(string.split("\\.")[0]);
//
//
//    }


}
