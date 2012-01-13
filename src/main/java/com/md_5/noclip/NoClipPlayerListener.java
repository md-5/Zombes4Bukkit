package com.md_5.noclip;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class NoClipPlayerListener extends PlayerListener {

    public NoClipPlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, this, Priority.Low, NoClip.instance);
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("noclip")) {
            player.sendMessage(NoClip.ALLOW_NOCLIP);
        }
    }
}
