package com.md_5.growth.blocks;

import com.md_5.growth.Spreader;
import java.util.Random;
import net.minecraft.server.Block;
import net.minecraft.server.BlockPumpkin;
import net.minecraft.server.World;

public class GrowthPumpkin extends BlockPumpkin {

    public GrowthPumpkin(final Block parent) {
        super(parent.id, parent.textureId, false);
    }

    @Override
    public void a(final World world, final int i, final int j, final int k, final Random random) {
        super.a(world, i, j, k, random);
        Spreader.handle(world, i, j, k, random, this);
    }
}
