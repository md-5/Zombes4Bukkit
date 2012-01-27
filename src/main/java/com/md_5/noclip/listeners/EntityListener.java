package com.md_5.noclip.listeners;

import com.md_5.noclip.NoClip;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityListener implements Listener {

    public EntityListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, NoClip.instance);
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        final Entity entity = event.getEntity();
        if (entity.getLastDamageCause().getCause() == DamageCause.SUFFOCATION && entity instanceof CraftPlayer && ((CraftPlayer) entity).getHandle().bQ) {
            event.setCancelled(true);
        }
    }
}
