package com.md_5.noclip;

import com.md_5.noclip.listeners.EntityListener;
import com.md_5.noclip.listeners.PlayerListener;
import com.md_5.zmod.BaseMod;
import net.minecraft.server.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class NoClip extends BaseMod {

    public NoClip() {
        super("NoClip");
    }

    @Override
    public void enable() {
        new Config();
        new EntityListener();
        new PlayerListener();
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
}
