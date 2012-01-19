package com.md_5.zmod;

import com.md_5.death.Death;
import com.md_5.growth.Growth;
import com.md_5.noclip.NoClip;
import java.util.HashSet;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    public static Plugin instance;
    public static Logger logger;
    public HashSet<BaseMod> mods = new HashSet<BaseMod>();

    @Override
    public void onLoad() {
        instance = this;
        logger = getLogger();
    }

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        mods.add(new Death());
        mods.add(new Growth());
        mods.add(new NoClip());
        for (BaseMod mod : mods) {
            mod.enable();
        }
    }

    public void onDisable() {
        for (BaseMod mod : mods) {
            mod.disable();
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        for (BaseMod mod : mods) {
            mod.onCommand(sender, command, label, args);
        }
        return true;
    }
}
