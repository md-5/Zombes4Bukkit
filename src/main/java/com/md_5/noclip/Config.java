package com.md_5.noclip;

import com.md_5.zmod.BaseConfig;

public class Config extends BaseConfig {

    public static String ALLOW_NOCLIP = "§6Zombe's NoClip is allowed on this server §f §f §4 §0 §9 §6";
    public static boolean MESSAGES;

    public Config() {
        MESSAGES = conf.getBoolean("noclip.messages");
    }
}
