package com.md_5.noclip;

import com.md_5.noclip.config.Config;
import com.md_5.noclip.listeners.NoClipEntityListener;
import com.md_5.noclip.listeners.NoClipPlayerListener;
import com.md_5.noclip.updaters.NMSUpdater;
import com.md_5.noclip.updaters.NoClipUpdater;
import com.md_5.noclip.updaters.SpoutUpdater;
import com.md_5.zmod.BaseMod;
import com.md_5.zmod.Plugin;
import net.minecraft.server.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class NoClip extends BaseMod {

    public NoClipUpdater updater;

    @Override
    public void enable() {
        updater = (Plugin.instance.getServer().getPluginManager().isPluginEnabled("Spout")) ? new SpoutUpdater() : new NMSUpdater();
        new Config();
        new NoClipEntityListener();
        new NoClipPlayerListener();
        logger.info("Zombe's NoClip for Bukkit by md_5 enabled");
    }

    @Override
    public void disable() {
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

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
