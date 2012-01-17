package com.md_5.noclip;

import com.md_5.noclip.config.Config;
import com.md_5.noclip.listeners.NoClipEntityListener;
import com.md_5.noclip.listeners.NoClipPlayerListener;
import com.md_5.noclip.updaters.NMSUpdater;
import com.md_5.noclip.updaters.NoClipUpdater;
import com.md_5.noclip.updaters.SpoutUpdater;
import java.util.logging.Logger;
import net.minecraft.server.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class NoClip extends JavaPlugin {

    public static NoClip instance;
    public static Logger logger;
    private static NoClipUpdater updater;

    public NoClip() {
        instance = this;
    }

    public void onEnable() {
        logger = getLogger();
        updater = (getServer().getPluginManager().isPluginEnabled("Spout")) ? new SpoutUpdater() : new NMSUpdater();
        new Config();
        new NoClipEntityListener();
        new NoClipPlayerListener();
        logger.info("Zombe's NoClip for Bukkit by md_5 enabled");
    }

    public void onDisable() {
        logger.info("Zombe's NoClip for Bukkit by md_5 disabled");
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
        updater.updateNetServerHandler(player);
        if (Config.MESSAGES) {
            player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully enabled");
        }
    }

    private void disableNoClip(final EntityPlayer player) {
        player.bQ = false;
        updater.updateNetServerHandler(player);
        if (Config.MESSAGES) {
            player.getPlayer().sendMessage(ChatColor.GREEN + "NoClip successfully disabled");
        }
    }
}
