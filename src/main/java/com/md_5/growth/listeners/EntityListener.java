package com.md_5.growth.listeners;

import com.md_5.growth.Handler;
import com.md_5.zmod.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

public class EntityListener implements Listener {

    public EntityListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Plugin.instance);
    }

    @EventHandler(event = ItemSpawnEvent.class, priority = EventPriority.NORMAL)
    public void onItemSpawn(final ItemSpawnEvent event) {
        Handler.toHandle.add((Item) event.getEntity());
    }

    @EventHandler(event = ItemDespawnEvent.class, priority = EventPriority.NORMAL)
    public void onItemDespawn(final ItemDespawnEvent event) {
        Handler.handle((Item) event.getEntity());
    }
}
