package com.md_5.death;

import com.md_5.zmod.BaseMod;

public class Death extends BaseMod {

    public Death() {
        super("Death");
    }

    @Override
    public void enable() {
        new DeathListener();
    }

    @Override
    public void disable() {
    }
}
