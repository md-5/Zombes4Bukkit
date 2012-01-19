package net.minecraft.server;

public class GrowthBlock {

    public static Block c(Block block, float f) {
        return block.c(f);
    }

    public static Block a(Block block, StepSound stepsound) {
        block.stepSound = stepsound;
        return block;
    }

    public static Block a(Block block, float f) {
        Block.lightEmission[block.id] = (int) (15.0F * f);
        return block;
    }

    public static Block i(Block block) {
        Block.t[block.id] = true;
        return block;
    }

    public static Block p(Block block) {
        block.bS = false;
        return block;
    }
}
