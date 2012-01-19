package com.md_5.noclip;

import com.md_5.zmod.BaseConfig;

public class Config extends BaseConfig {

    public static String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    public static boolean MESSAGES;
    static String ENABLE_MESSAGE, DISABLE_MESSAGE;

    public Config() {

        //ALLOW_NOCLIP = conf.getString("magicToken").replace('&', '§');
        MESSAGES = conf.getBoolean("messages");
        //ENABLE_MESSAGE = conf.getString("enableMessage".replace('&', '§'));
        //DISABLE_MESSAGE = conf.getString("disableMessage").replace('&', '§');
    }
}
