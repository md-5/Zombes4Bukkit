package com.md_5.death;

import com.md_5.zmod.Plugin;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DeathListener implements Listener {

    HashMap<String, PlayerInventory> inventories = new HashMap<String, PlayerInventory>();

    public DeathListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Plugin.instance);
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            if (Config.dropInv && player.hasPermission(Permissions.keepInventory)) {
                final PlayerInventory inventory = player.getInventory();
                inventories.put(player.getName(), inventory);
                inventory.clear();
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        if (Config.dropInv && player.hasPermission(Permissions.keepInventory)) {
            final PlayerInventory inventory = inventories.get(player.getName());
            if (inventory != null) {
                for (int i = 0; i <= inventory.getSize(); i++) {
                    final ItemStack stack = inventory.getItem(i);
                    if (stack.getType() == Material.AIR) {
                        player.getInventory().setItem(i, null);
                    } else {
                        player.getInventory().setItem(i, stack);
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Your inventory has been restored");
            }
        }
        if (!player.hasPermission(Permissions.penaltyExempt)) {
            player.sendMessage(ChatColor.RED + "You have had a penalty of " + Config.penalty + " applied.");
            player.damage(Config.penalty);
        }
    }
}
