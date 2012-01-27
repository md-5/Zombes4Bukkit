package com.md_5.growth;

import com.md_5.zmod.BaseConfig;

public class Config extends BaseConfig {

    public static int flower, shroom, pumpkin, sapling, reed;
    public static int rootingSpace, rootingTime;
    public static boolean rooting, planting;

    public Config() {
        // Spreading
        flower = conf.getInt("growth.flower");
        shroom = conf.getInt("growth.shroom");
        pumpkin = conf.getInt("growth.pumpkin");
        sapling = conf.getInt("growth.sapling");
        reed = conf.getInt("growth.reed");
        // Options
        rooting = conf.getBoolean("growth.rooting");
        planting = conf.getBoolean("growth.planting");
        // Rooting
        rootingSpace = conf.getInt("growth.rootingSpace");
        rootingTime = conf.getInt("growth.rootingTime");
    }
}
