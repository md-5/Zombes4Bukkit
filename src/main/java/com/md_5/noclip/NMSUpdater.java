package com.md_5.noclip;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetServerHandler;

public class NMSUpdater {

    public static void updateNetServerHandler(final EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        final NetServerHandler handler = new NoClipNetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }

    public static void resetNetServerHandler(final EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        final NetServerHandler handler = new NetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }
}
