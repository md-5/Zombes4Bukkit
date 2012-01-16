package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetServerHandler;

public class NMSUpdater implements NoClipUpdater {

    public void updateNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NoClipNetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }

    public void resetNetServerHandler(EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        NetServerHandler handler = new NetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }
}
