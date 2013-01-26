package net.md_5.noclip;

import net.minecraft.server.v1_4_R1.EntityPlayer;
import net.minecraft.server.v1_4_R1.PlayerConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
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
        // player.Y = true;
        player.playerConnection.disconnected = true;
        PlayerConnection handler = new NoClipPlayerConnection(player.server, player.playerConnection.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.ae().a(handler);
    }

    private void disableNoClip(EntityPlayer player) {
        // player.Y = false;
        player.playerConnection.disconnected = true;
        PlayerConnection handler = new PlayerConnection(player.server, player.playerConnection.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.ae().a(handler);
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
            EntityDamageEvent lastCause = entity.getLastDamageCause();
            if (lastCause != null) {
                if (lastCause.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && entity instanceof CraftPlayer && ((CraftPlayer) entity).getHandle().Y) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
