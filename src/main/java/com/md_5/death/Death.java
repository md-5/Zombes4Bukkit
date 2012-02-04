package com.md_5.death;

import com.md_5.zmod.BaseMod;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Death extends BaseMod implements Listener {

    private HashMap<String, PlayerInventory> inventories = new HashMap<String, PlayerInventory>();

    public Death() {
        super("Death");
    }

    @Override
    public void enable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        if (event instanceof PlayerDeathEvent) {
            final PlayerDeathEvent pd = (PlayerDeathEvent) event;
            final Player player = (Player) event.getEntity();
            if (Config.dropInv && player.hasPermission(Permissions.keepInventory)) {
                final PlayerInventory inventory = player.getInventory();
                inventories.put(player.getName(), inventory);
                inventory.clear();
            }
            if (Config.loseExp && !player.hasPermission(Permissions.keepExp)) {
                pd.setKeepLevel(false);
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
            player.sendMessage(ChatColor.RED + "You have had a penalty of " + Config.penalty + " hp applied.");
            player.damage(Config.penalty);
        }
    }
}
