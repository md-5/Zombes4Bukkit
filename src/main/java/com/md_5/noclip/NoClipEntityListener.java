package com.md_5.noclip;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class NoClipEntityListener extends EntityListener {

    public NoClipEntityListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_DAMAGE, this, Priority.Normal, NoClip.instance);
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity.getLastDamageCause().getCause() == DamageCause.SUFFOCATION && entity instanceof Player && ((CraftPlayer) entity).getHandle().bQ) {
            event.setCancelled(true);
        }
    }
}
