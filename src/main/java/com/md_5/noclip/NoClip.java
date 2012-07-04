package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetServerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoClip extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof CraftPlayer) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("enabled")) {
                    enableNoClip(((CraftPlayer) sender).getHandle());
                } else if (args[0].equalsIgnoreCase("disabled")) {
                    disableNoClip(((CraftPlayer) sender).getHandle());
                }
            }
        }
        return true;
    }

    private void enableNoClip(EntityPlayer player) {
        player.bQ = true;
        updateNetServerHandler(player);
    }

    private void disableNoClip(EntityPlayer player) {
        player.bQ = false;
        resetNetServerHandler(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("zombes.noclip") && player instanceof CraftPlayer) {
            player.sendMessage("§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6");
        }
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity != null) {
            if (entity.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && entity instanceof CraftPlayer && ((CraftPlayer) entity).getHandle().bQ) {
                event.setCancelled(true);
            }
        }
    }

    public static void updateNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NoClipNetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }

    public static void resetNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }
}
