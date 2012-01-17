package com.md_5.noclip.updaters;

import com.md_5.noclip.handlers.NoClipSpoutNetServerHandler;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetServerHandler;
import org.getspout.spout.SpoutNetServerHandler;

public class SpoutUpdater implements NoClipUpdater {

    public void updateNetServerHandler(final EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        final NetServerHandler handler = new NoClipSpoutNetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }

    public void resetNetServerHandler(final EntityPlayer player) {
        player.netServerHandler.disconnected = true;
        final NetServerHandler handler = new SpoutNetServerHandler(player.server, player.netServerHandler.networkManager, player);
        handler.a(player.locX, player.locY, player.locZ, player.yaw, player.pitch);
        player.server.networkListenThread.a(handler);
    }
}
