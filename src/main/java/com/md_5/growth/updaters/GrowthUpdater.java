package com.md_5.growth.updaters;

import net.minecraft.server.Block;

public abstract class GrowthUpdater {

    public abstract void updateClass(final int id, final Block original);

    public void update() {
    }
}
