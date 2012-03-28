package com.md_5.noclip;

import com.md_5.zmod.BaseMod;
import java.lang.reflect.Constructor;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.NetServerHandler;
import net.minecraft.server.NetworkManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class NoClip extends BaseMod implements Listener {

    public String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    public boolean MESSAGES;
    public static Constructor<? extends NetServerHandler> nsh;

    public NoClip() {
        // TEST
        super("NoClip");
    }

    @Override
    public void enable() {
        MESSAGES = getConfig().getBoolean("noclip.messages");
        try {
            Class<? extends NetServerHandler> c;
            if (getServer().getPluginManager().isPluginEnabled("Spout")) {
                c = SpoutNoClipNetServerHandler.class;
            } else {
                c = NoClipNetServerHandler.class;
            }
            nsh = c.getConstructor(MinecraftServer.class, NetworkManager.class, EntityPlayer.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
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

    private void enableNoClip(final EntityPlayer player) {
        player.bQ = true;
        updateNetServerHandler(player);
        if (MESSAGES) {
            player.getBukkitEntity().sendMessage(ChatColor.GREEN + "NoClip successfully enabled");
        }
    }

    private void disableNoClip(final EntityPlayer player) {
        player.bQ = false;
        resetNetServerHandler(player);
        if (MESSAGES) {
            player.getBukkitEntity().sendMessage(ChatColor.GREEN + "NoClip successfully disabled");
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission("zombes.noclip") && player instanceof CraftPlayer) {
            player.sendMessage(ALLOW_NOCLIP);
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
        NetServerHandler handler = null;
        try {
            handler = NoClip.nsh.newInstance(player.server, player.netServerHandler.networkManager, player);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
