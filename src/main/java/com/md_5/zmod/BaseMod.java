package com.md_5.zmod;

public abstract class BaseMod extends Plugin {

    public BaseMod(String name) {
        this.name = name;
    }
    public String name;

    public abstract void enable();

    public void disable() {
    }
}
