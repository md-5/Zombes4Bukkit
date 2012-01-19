package com.md_5.growth;

import com.md_5.growth.listeners.EntityListener;
import com.md_5.growth.updaters.GrowthUpdater;
import com.md_5.growth.updaters.NMSUpdater;
import com.md_5.growth.updaters.SpoutUpdater;
import com.md_5.zmod.BaseMod;

public class Growth extends BaseMod {

    public static boolean spout = false;
    public GrowthUpdater updater;
    final static int rootTime = 10;

    @Override
    public void enable() {
        updater = (instance.getServer().getPluginManager().isPluginEnabled("Spout")) ? new SpoutUpdater() : new NMSUpdater();
        instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Rooter(), 20, 20);
        new EntityListener();
        updater.update();
    }

    @Override
    public void disable() {
        instance.getServer().getScheduler().cancelTasks(instance);
    }

    @Override
    public void init() {
    }

    /*
     * @Override public void update() { try { // Yellow flower GrowthFlower
     * YELLOW_FLOWER = new GrowthFlower(37, 13); YELLOW_FLOWER = (GrowthFlower)
     * c(YELLOW_FLOWER, 0.0F); YELLOW_FLOWER = (GrowthFlower) a(YELLOW_FLOWER,
     * Block.g).a("flower");
     * setFinalStatic(Block.class.getDeclaredField("YELLOW_FLOWER"),
     * YELLOW_FLOWER); // Red rose GrowthFlower RED_ROSE = new GrowthFlower(38,
     * 12); RED_ROSE = (GrowthFlower) c(RED_ROSE, 0.0F); RED_ROSE =
     * (GrowthFlower) a(RED_ROSE, Block.g).a("rose");
     * setFinalStatic(Block.class.getDeclaredField("RED_ROSE"), RED_ROSE); //
     * Brown Mushroom GrowthMushroom BROWN_MUSHROOM = new GrowthMushroom(39,
     * 29); BROWN_MUSHROOM = (GrowthMushroom) c(BROWN_MUSHROOM, 0.0F);
     * BROWN_MUSHROOM = (GrowthMushroom) a(BROWN_MUSHROOM, Block.g);
     * BROWN_MUSHROOM = (GrowthMushroom) a(BROWN_MUSHROOM,
     * 0.125F).a("mushroom");
     * setFinalStatic(Block.class.getDeclaredField("BROWN_MUSHROOM"),
     * BROWN_MUSHROOM); // Red Mushroom GrowthMushroom RED_MUSHROOM = new
     * GrowthMushroom(40, 28); RED_MUSHROOM = (GrowthMushroom) c(RED_MUSHROOM,
     * 0.0F); RED_MUSHROOM = (GrowthMushroom) a(RED_MUSHROOM,
     * Block.g).a("mushroom");
     * setFinalStatic(Block.class.getDeclaredField("RED_MUSHROOM"),
     * RED_MUSHROOM); // Pumpkin GrowthPumpkin PUMPKIN = new GrowthPumpkin(86,
     * 102, false); PUMPKIN = (GrowthPumpkin) c(PUMPKIN, 1.0F); PUMPKIN =
     * (GrowthPumpkin) a(PUMPKIN, Block.e).a("pumpkin"); PUMPKIN =
     * (GrowthPumpkin) i(PUMPKIN);
     * setFinalStatic(Block.class.getDeclaredField("PUMPKIN"), PUMPKIN); // Reed
     * GrowthReed SUGAR_CANE_BLOCK = new GrowthReed(83, 73); SUGAR_CANE_BLOCK =
     * (GrowthReed) c(SUGAR_CANE_BLOCK, 0.0F); SUGAR_CANE_BLOCK = (GrowthReed)
     * a(SUGAR_CANE_BLOCK, Block.g).a("reeds"); SUGAR_CANE_BLOCK = (GrowthReed)
     * p(SUGAR_CANE_BLOCK);
     * setFinalStatic(Block.class.getDeclaredField("SUGAR_CANE_BLOCK"),
     * SUGAR_CANE_BLOCK); } catch (Exception ex) { ex.printStackTrace(); } }
     */
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
