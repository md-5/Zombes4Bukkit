package com.md_5.noclip.config;

import com.md_5.noclip.NoClip;
import org.bukkit.configuration.file.FileConfiguration;

public final class Config {

    public static String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    public static boolean MESSAGES;
    static String ENABLE_MESSAGE, DISABLE_MESSAGE;

    public Config() {
        final FileConfiguration conf = NoClip.instance.getConfig();
        conf.options().copyDefaults(true);
        NoClip.instance.saveConfig();
        //ALLOW_NOCLIP = conf.getString("magicToken").replace('&', '§');
        MESSAGES = conf.getBoolean("messages");
        //ENABLE_MESSAGE = conf.getString("enableMessage".replace('&', '§'));
        //DISABLE_MESSAGE = conf.getString("disableMessage").replace('&', '§');
    }
}
