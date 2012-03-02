package com.md_5.growth;

import com.md_5.growth.blocks.*;
import net.minecraft.server.Block;
import org.bukkit.Material;

public class GrowthUpdater {

    public static void update() {
        for (int id = 0; id < Block.byId.length; id++) {
            if (Block.byId[id] == null) {
                continue;
            }
            final Block original = Block.byId[id];
            boolean oldn = Block.n[id];
            int oldq = Block.lightBlock[id];
            boolean oldp = Block.p[id];
            int olds = Block.lightEmission[id];
            boolean oldr = Block.r[id];
            if (id == Material.BROWN_MUSHROOM.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthMushroom(original);
            }
            if (id == Material.RED_MUSHROOM.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthMushroom(original);
            }
            if (id == Material.RED_ROSE.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthFlower(original);
            }
            if (id == Material.PUMPKIN.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthPumpkin(original);
            }
            if (id == Material.SAPLING.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthSapling(original);
            }
            if (id == Material.SUGAR_CANE_BLOCK.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthReed(original);
            }
            if (id == Material.YELLOW_FLOWER.getId()) {
                Block.byId[id] = null;
                Block.byId[id] = new GrowthFlower(original);
            }
            Block.n[id] = oldn;
            Block.p[id] = oldp;
            Block.lightBlock[id] = oldq;
            Block.r[id] = oldr;
            Block.lightEmission[id] = olds;
        }
    }
}
