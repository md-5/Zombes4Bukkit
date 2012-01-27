package com.md_5.death;

import com.md_5.zmod.BaseConfig;

public class Config extends BaseConfig {

    public static boolean dropInv, loseExp;
    public static int penalty;

    public Config() {
        dropInv = conf.getBoolean("death.dropInv");
        loseExp = conf.getBoolean("death.loseExp");
        penalty = conf.getInt("death.healthPenalty");
    }
}
