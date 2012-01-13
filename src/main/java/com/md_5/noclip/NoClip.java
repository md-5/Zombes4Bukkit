package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetServerHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NoClip extends JavaPlugin {

    protected static final String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    protected static NoClip instance;

    public NoClip() {
        instance = this;
    }

    public void onEnable() {
        new NoClipEntityListener();
        new NoClipPlayerListener();
        System.out.println("Zombe's NoClip for Bukkit by md_5 enabled");
    }

    public void onDisable() {
        System.out.println("Zombe's NoClip for Bukkit by md_5 disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        EntityPlayer player = ((CraftPlayer) sender).getHandle();
        if (!player.getPlayer().hasPermission("noclip")) {
            player.getPlayer().sendMessage(ChatColor.RED + "Hacking you client still doesn't allow you to do that!");
            return true;
        }
        if (args[0].equalsIgnoreCase("enabled")) {
            enableNoClip(player);
        } else if (args[0].equalsIgnoreCase("disabled")) {
            disableNoClip(player);
        }
        return true;
    }

    private static void enableNoClip(EntityPlayer player) {
        player.bQ = true;
        updateNetServerHandler(player);
        player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully enabled");
    }

    private static void disableNoClip(EntityPlayer player) {
        player.bQ = false;
        resetNetServerHandler(player);
        player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully disabled");
    }

    private static void updateNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NoClipNetServerHandler(player.b, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.b.networkListenThread.a(handler);
    }

    private static void resetNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NetServerHandler(player.b, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.b.networkListenThread.a(handler);
    }
}
