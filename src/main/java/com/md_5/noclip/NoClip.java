package com.md_5.noclip;

import com.md_5.zmod.BaseMod;
import net.minecraft.server.EntityPlayer;
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

    public NoClip() {
        super("NoClip");
    }

    @Override
    public void enable() {
        new Config();
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof CraftPlayer) {
            if (args[0].equalsIgnoreCase("enabled")) {
                enableNoClip(((CraftPlayer) sender).getHandle());
            } else if (args[0].equalsIgnoreCase("disabled")) {
                disableNoClip(((CraftPlayer) sender).getHandle());
            }
        }
        return true;
    }

    private void enableNoClip(final EntityPlayer player) {
        player.bQ = true;
        NMSUpdater.updateNetServerHandler(player);
        if (Config.MESSAGES) {
            player.getBukkitEntity().sendMessage(ChatColor.GREEN + "NoClip successfully enabled");
        }
    }

    private void disableNoClip(final EntityPlayer player) {
        player.bQ = false;
        NMSUpdater.updateNetServerHandler(player);
        if (Config.MESSAGES) {
            player.getBukkitEntity().sendMessage(ChatColor.GREEN + "NoClip successfully disabled");
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPermission(Permissions.PERMISSION) && player instanceof CraftPlayer) {
            player.sendMessage(Config.ALLOW_NOCLIP);
        }
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        final Entity entity = event.getEntity();
        if (entity.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && entity instanceof CraftPlayer && ((CraftPlayer) entity).getHandle().bQ) {
            event.setCancelled(true);
        }
    }
}
