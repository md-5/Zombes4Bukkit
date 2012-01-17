package com.md_5.noclip.listeners;

import com.md_5.noclip.NoClip;
import com.md_5.noclip.config.Config;
import com.md_5.noclip.config.Variables;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NoClipPlayerListener implements Listener {

    public NoClipPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, NoClip.instance);
    }

    @EventHandler(event = PlayerJoinEvent.class, priority = EventPriority.NORMAL)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission(Variables.PERMISSION) && player instanceof CraftPlayer) {
            player.sendMessage(Config.ALLOW_NOCLIP);
        }
    }
}
