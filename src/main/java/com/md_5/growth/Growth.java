package com.md_5.growth;

import com.md_5.zmod.BaseMod;

public class Growth extends BaseMod {

    public Growth() {
        super("Growth");
    }

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
