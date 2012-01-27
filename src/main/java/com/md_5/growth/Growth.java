package com.md_5.growth;

import com.md_5.growth.listeners.EntityListener;
import com.md_5.zmod.BaseMod;

public class Growth extends BaseMod {

    public static boolean spout = false;
    final static int rootTime = 10;

    @Override
    public void enable() {
        GrowthUpdater.update();
        instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Rooter(), 20, 20);
        new EntityListener();
    }

    @Override
    public void disable() {
        instance.getServer().getScheduler().cancelTasks(instance);
    }
}
