package com.md_5.growth;

import com.md_5.zmod.BaseMod;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

public class Growth extends BaseMod implements Listener {

    public static int flower, shroom, pumpkin, sapling, reed;
    public static int rootingSpace, rootingTime;
    public static boolean rooting, planting;

    public Growth() {
        super("Growth");
    }

    @Override
    public void enable() {
        final FileConfiguration conf = getConfig();
        // Spreading
        flower = conf.getInt("growth.flower");
        shroom = conf.getInt("growth.shroom");
        pumpkin = conf.getInt("growth.pumpkin");
        sapling = conf.getInt("growth.sapling");
        reed = conf.getInt("growth.reed");
        // Options
        rooting = conf.getBoolean("growth.rooting");
        planting = conf.getBoolean("growth.planting");
        // Rooting
        rootingSpace = conf.getInt("growth.rootingSpace");
        rootingTime = conf.getInt("growth.rootingTime");
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
        final Item item = event.getEntity();
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
