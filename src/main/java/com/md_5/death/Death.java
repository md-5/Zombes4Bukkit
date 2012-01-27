package com.md_5.death;

import com.md_5.zmod.BaseMod;

public class Death extends BaseMod {

    @Override
    public void enable() {
        new DeathListener();
    }

    @Override
    public void disable() {
    }
}
