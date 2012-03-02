package com.md_5.growth;

import com.md_5.zmod.BaseMod;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

public class Growth extends BaseMod implements Listener {

    public Growth() {
        super("Growth");
    }

    @Override
    public void enable() {
        GrowthUpdater.update();
        getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Rooter(), 20, 20);
        getServer().getPluginManager().registerEvents(this, instance);
    }

    @Override
    public void disable() {
        getServer().getScheduler().cancelTasks(instance);
    }

    @EventHandler
    public void onItemSpawn(final ItemSpawnEvent event) {
        final Item item = (Item) event.getEntity();
        final int type = item.getItemStack().getTypeId();
        if (type == 6 || type == 295) {
            Rooter.toHandle.add(item);
        }
    }

    @EventHandler
    public void onItemDespawn(final ItemDespawnEvent event) {
        final Item item = (Item) event.getEntity();
        final int type = item.getItemStack().getTypeId();
        if (type == 6 || type == 295) {
            Rooter.handle(item);
        }
    }
}
