package com.md_5.growth.blocks.nms;

import com.md_5.growth.Spreader;
import java.util.Random;
import net.minecraft.server.Block;
import net.minecraft.server.BlockMushroom;
import net.minecraft.server.World;

public class GrowthMushroom extends BlockMushroom {

    public GrowthMushroom(final Block parent) {
        super(parent.id, parent.textureId);
    }

    @Override
    public void a(final World world, final int i, final int j, final int k, final Random random) {
        super.a(world, i, j, k, random);
        Spreader.handle(world, i, j, k, random, this);
    }
}
