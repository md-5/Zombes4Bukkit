package com.md_5.growth;

import java.util.Random;
import net.minecraft.server.Block;
import net.minecraft.server.World;
import org.bukkit.Material;

public class Spreader {

    public static final int MAX_SHROOM = 13, MIN_OTHER = 8;
    public static int optGrowthFlower = 1, optGrowthShroom = 1, optGrowthPumpkin = 1, optGrowthSappling = 1, optGrowthReed = 1, optGrowthRootingSpace = 1, optGrowthRootingTime = 1;

    public static void handle(final World world, final int i, final int j, final int k, final Random random, final Block block) {
        int chance = 1;
        final int id = block.id;
        if (id == Material.BROWN_MUSHROOM.getId() || id == Material.RED_MUSHROOM.getId()) {
            chance = optGrowthShroom;
        }
        if (id == Material.RED_ROSE.getId() || id == Material.YELLOW_FLOWER.getId()) {
            chance = optGrowthFlower;
        }
        if (id == Material.PUMPKIN.getId()) {
            chance = optGrowthPumpkin;
        }
        if (id == Material.SAPLING.getId()) {
            chance = optGrowthSappling;
        }
        if (id == Material.SUGAR_CANE_BLOCK.getId()) {
            chance = optGrowthReed;
        }
        if (random.nextInt(chance) == 0) {
            final int toX = random.nextInt(3) - 1;
            final int toZ = random.nextInt(3) - 1;
            if (world.getTypeId(i + toX, j - 1, k + toZ) != 3 || world.getTypeId(i + toX, j, k + toZ) != 0) {
                return;
            }
            if (!checkLight(world.getLightLevel(i + toX, j, k + toZ), id)) {
                return;
            }
            world.setTypeIdAndData(i + toX, j, k + toZ, world.getTypeId(i, j, k), world.getData(i, j, k));
        }
    }

    private static boolean checkLight(final int level, final int id) {
        if (id == Material.BROWN_MUSHROOM.getId() || id == Material.RED_MUSHROOM.getId()) {
            if (level > Spreader.MAX_SHROOM) {
                return false;
            }
        }
        if (level < MIN_OTHER) {
            return false;
        }
        return true;
    }
}
