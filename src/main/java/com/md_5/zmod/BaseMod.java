package com.md_5.zmod;

public abstract class BaseMod extends Plugin {

    public abstract void enable();

    public abstract void disable();

    public abstract void init();

    public abstract void update();
}
