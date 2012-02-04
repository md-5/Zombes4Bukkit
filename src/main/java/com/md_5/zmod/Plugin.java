package com.md_5.zmod;

import com.md_5.boom.Boom;
import com.md_5.death.Death;
import com.md_5.growth.Growth;
import com.md_5.noclip.NoClip;
import com.md_5.spawn.Spawn;
import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
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

    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        final FileConfiguration conf = getConfig();
        conf.options().copyDefaults(true);
        mods.add(new Boom());
        mods.add(new Death());
        mods.add(new Growth());
        mods.add(new NoClip());
        mods.add(new Spawn());
        for (BaseMod mod : mods) {
            if (conf.getBoolean(mod.name.toLowerCase() + ".enabled")) {
                logger.info(mod.name + "Enabled");
                mod.enable();
            }
        }
    }

    @Override
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
