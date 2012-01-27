package com.md_5.growth;

import java.util.HashSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Rooter implements Runnable {

    final static float growthSqrRadius = (0.5f + Config.rootingSpace) * (0.5f + Config.rootingSpace);
    public final static HashSet<Item> toHandle = new HashSet<Item>();

    public void run() {
        final HashSet<Item> toRemove = new HashSet<Item>();
        for (final Item i : toHandle) {
            System.out.println(i.getTicksLived());
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
        if (block.getLightLevel() < 8) {
            return;
        }
        final ItemStack itemStack = item.getItemStack();
        if (itemStack.getAmount() != 1) {
            return;
        }
        final int blockBelow = world.getBlockTypeIdAt(x, y - 1, z);
        if (itemStack.getTypeId() == Material.SEEDS.getId()) {
            if (blockBelow == Material.SOIL.getId()) {
                block.setTypeId(Material.CROPS.getId());
                item.remove();
            }
        }
        if (itemStack.getTypeId() == Material.SAPLING.getId()) {
            if (blockBelow == Material.DIRT.getId() || blockBelow == Material.GRASS.getId()) {
                for (int x2 = -Config.rootingSpace; x2 <= Config.rootingSpace; x2++) {
                    for (int z2 = -Config.rootingSpace; z2 <= Config.rootingSpace; z2++) {
                        if (x2 * x2 + z2 * z2 > growthSqrRadius) {
                            continue;
                        }
                        for (int y2 = -Config.rootingSpace; y2 <= Config.rootingSpace; y2++) {
                            final int adjacentBlock = world.getBlockTypeIdAt(x + x2, y + y2, z + z2);
                            if (adjacentBlock == Material.LEAVES.getId() || adjacentBlock == Material.LOG.getId()) {
                                return;
                            }
                        }
                    }
                }
                block.setTypeIdAndData(Material.SAPLING.getId(), (byte) itemStack.getDurability(), true);
                item.remove();
            }
        }
    }
}
