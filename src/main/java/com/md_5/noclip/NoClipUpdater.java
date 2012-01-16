package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;

public interface NoClipUpdater {

    public void updateNetServerHandler(EntityPlayer player);

    public void resetNetServerHandler(EntityPlayer player);
}
