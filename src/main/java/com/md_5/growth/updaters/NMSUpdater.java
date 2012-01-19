package com.md_5.growth.updaters;

import com.md_5.growth.blocks.nms.*;
import net.minecraft.server.Block;
import net.minecraft.server.BlockFlower;
import net.minecraft.server.BlockMushroom;
import org.bukkit.Material;

public class NMSUpdater implements GrowthUpdater {

    public void update() {
        for (int id = 0; id < Block.byId.length; id++) {
            if (Block.byId[id] == null) {
                continue;
            }
            final Block original = Block.byId[id];
            final boolean oldn = Block.n[id];
            final boolean oldo = Block.o[id];
            final boolean oldTileEntity = Block.isTileEntity[id];
            final int oldq = Block.lightBlock[id];
            final boolean oldr = Block.r[id];
            final int olds = Block.lightEmission[id];
            final boolean oldt = Block.t[id];
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
            Block.o[id] = oldo;
            Block.isTileEntity[id] = oldTileEntity;
            Block.lightBlock[id] = oldq;
            Block.r[id] = oldr;
            Block.lightEmission[id] = olds;
            Block.t[id] = oldt;
        }
    }
}
