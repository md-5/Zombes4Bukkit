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
}
