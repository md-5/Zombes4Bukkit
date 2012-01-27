package com.md_5.noclip;

import com.md_5.noclip.listeners.EntityListener;
import com.md_5.noclip.listeners.PlayerListener;
import com.md_5.noclip.updaters.NMSUpdater;
import com.md_5.noclip.updaters.NoClipUpdater;
import com.md_5.noclip.updaters.SpoutUpdater;
import com.md_5.zmod.BaseMod;
import net.minecraft.server.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class NoClip extends BaseMod {

    public NoClipUpdater updater;

    public NoClip() {
        super("NoClip");
    }

    @Override
    public void enable() {
        updater = (instance.getServer().getPluginManager().isPluginEnabled("Spout")) ? new SpoutUpdater() : new NMSUpdater();
        new Config();
        new EntityListener();
        new PlayerListener();
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
}
