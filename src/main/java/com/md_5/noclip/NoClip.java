package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NoClip extends JavaPlugin {

    static final String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    static NoClip instance;
    private NoClipUpdater updater;

    public NoClip() {
        instance = this;
    }

    public void onEnable() {
        updater = (getServer().getPluginManager().isPluginEnabled("Spout")) ? new SpoutUpdater() : new NMSUpdater();
        new NoClipEntityListener();
        new NoClipPlayerListener();
        System.out.println("Zombe's NoClip for Bukkit by md_5 enabled");
    }

    public void onDisable() {
        System.out.println("Zombe's NoClip for Bukkit by md_5 disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args[0].equalsIgnoreCase("enabled")) {
                enableNoClip(((CraftPlayer) sender).getHandle());
            } else if (args[0].equalsIgnoreCase("disabled")) {
                disableNoClip(((CraftPlayer) sender).getHandle());
            }
        }
        return true;
    }

    private void enableNoClip(EntityPlayer player) {
        player.bQ = true;
        updater.updateNetServerHandler(player);
        player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully enabled");
    }

    private void disableNoClip(EntityPlayer player) {
        player.bQ = false;
        updater.updateNetServerHandler(player);
        player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully disabled");
    }
}
