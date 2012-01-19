package com.md_5.growth.blocks.spout;

import com.md_5.growth.Spreader;
import java.util.Random;
import net.minecraft.server.World;
import org.getspout.spout.block.mcblock.CustomMushroom;

public class GrowthMushroom extends CustomMushroom {

    public GrowthMushroom(final CustomMushroom parent) {
        super(parent);
    }

    @Override
    public void a(final World world, final int i, final int j, final int k, final Random random) {
        super.a(world, i, j, k, random);
        Spreader.handle(world, i, j, k, random, this);
    }
}