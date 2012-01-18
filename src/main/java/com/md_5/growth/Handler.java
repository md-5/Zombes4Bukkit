package com.md_5.growth;

import java.util.HashSet;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Handler implements Runnable {

    final static int rootSpace = 3;
    final static float growthSqrRadius = (0.5f + rootSpace) * (0.5f + rootSpace);
    public final static HashSet<Item> toHandle = new HashSet<Item>();

    public void run() {
        HashSet<Item> toRemove = new HashSet<Item>();
        for (Item i : toHandle) {
            if (i.getTicksLived() > Growth.rootTime * 20) {
                handle(i);
                toRemove.add(i);
            }
        }
        toHandle.removeAll(toRemove);
    }

    public static void handle(final Item item) {
        final Location location = item.getLocation();
        final World world = location.getWorld();
        final Block block = world.getBlockAt(location);
        final int x = location.getBlockX();
        final int y = location.getBlockY();
        final int z = location.getBlockZ();
        if (y < 0 || block.getTypeId() != 0) {
            return;
        }
        if (block.getLightLevel() < 8) {
            return;
        }
        final ItemStack itemStack = item.getItemStack();
        if (itemStack.getAmount() != 1) {
            return;
        }
        final int blockBelow = world.getBlockTypeIdAt(x, y - 1, z);
        if (itemStack.getTypeId() == 295) {
            if (blockBelow == 60) {
                block.setTypeId(59);
                item.remove();
            }
        }
        if (itemStack.getTypeId() == 6) {
            if (blockBelow == 2 || blockBelow == 3) {
                for (int x2 = -rootSpace; x2 <= rootSpace; x2++) {
                    for (int z2 = -rootSpace; z2 <= rootSpace; z2++) {
                        if (x2 * x2 + z2 * z2 > growthSqrRadius) {
                            return;
                        }
                        for (int y2 = -rootSpace; y2 <= rootSpace; y2++) {
                            final int adjacentBlock = world.getBlockTypeIdAt(x + x2, y + y2, z + z2);
                            if (adjacentBlock == 17 || adjacentBlock == 6) {
                                return;
                            }
                        }
                    }
                }
                block.setTypeIdAndData(6, (byte) itemStack.getDurability(), true);
                item.remove();
            }
        }
    }
}
