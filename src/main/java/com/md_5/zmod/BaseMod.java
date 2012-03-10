package com.md_5.zmod;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class BaseMod {

    public final Plugin instance = Plugin.instance;
    public String name;

    public BaseMod(String name) {
        this.name = name;
    }

    public void enable() {
    }

    public void disable() {
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        return false;
    }

    public Server getServer() {
        return instance.getServer();
    }

    public FileConfiguration getConfig() {
        return instance.getConfig();
    }
}
