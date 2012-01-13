package com.md_5.noclip;

import net.minecraft.server.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NoClipNetServerHandler extends NetServerHandler {

    private MinecraftServer minecraftServer;
    private int g;
    private double x;
    private double y;
    private double z;
    private boolean checkMovement = true;

    public NoClipNetServerHandler(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
        this.minecraftServer = minecraftserver;
        this.server = minecraftserver.server;
    }
    private final CraftServer server;
    private double lastPosX = Double.MAX_VALUE;
    private double lastPosY = Double.MAX_VALUE;
    private double lastPosZ = Double.MAX_VALUE;
    private float lastPitch = Float.MAX_VALUE;
    private float lastYaw = Float.MAX_VALUE;
    private boolean justTeleported = false;

    @Override
    public void a(Packet10Flying packet10flying) {
        WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

        if (!this.player.j) {
            double d0;

            if (!this.checkMovement) {
                d0 = packet10flying.y - this.y;
                if (packet10flying.x == this.x && d0 * d0 < 0.01D && packet10flying.z == this.z) {
                    this.checkMovement = true;
                }
            }

            Player player = this.getPlayer();
            Location from = new Location(player.getWorld(), lastPosX, lastPosY, lastPosZ, lastYaw, lastPitch);
            Location to = player.getLocation().clone();

            if (packet10flying.h && !(packet10flying.h && packet10flying.y == -999.0D && packet10flying.stance == -999.0D)) {
                to.setX(packet10flying.x);
                to.setY(packet10flying.y);
                to.setZ(packet10flying.z);
            }

            if (packet10flying.hasLook) {
                to.setYaw(packet10flying.yaw);
                to.setPitch(packet10flying.pitch);
            }

            double delta = Math.pow(this.lastPosX - to.getX(), 2) + Math.pow(this.lastPosY - to.getY(), 2) + Math.pow(this.lastPosZ - to.getZ(), 2);
            float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch());

            if ((delta > 1f / 256 || deltaAngle > 10f) && (this.checkMovement && !this.player.dead)) {
                this.lastPosX = to.getX();
                this.lastPosY = to.getY();
                this.lastPosZ = to.getZ();
                this.lastYaw = to.getYaw();
                this.lastPitch = to.getPitch();

                if (from.getX() != Double.MAX_VALUE) {
                    PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
                    this.server.getPluginManager().callEvent(event);

                    if (event.isCancelled()) {
                        this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(from.getX(), from.getY() + 1.6200000047683716D, from.getY(), from.getZ(), from.getYaw(), from.getPitch(), false));
                        return;
                    }

                    if (!to.equals(event.getTo()) && !event.isCancelled()) {
                        this.player.getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
                        return;
                    }

                    if (!from.equals(this.getPlayer().getLocation()) && this.justTeleported) {
                        this.justTeleported = false;
                        return;
                    }
                }
            }

            if (Double.isNaN(packet10flying.x) || Double.isNaN(packet10flying.y) || Double.isNaN(packet10flying.z) || Double.isNaN(packet10flying.stance)) {
                player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
                System.err.println(player.getName() + " was caught trying to crash the server with an invalid position.");
                player.kickPlayer("Nope!");
                return;
            }

            if (this.checkMovement && !this.player.dead) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.player.vehicle != null) {
                    float f = this.player.yaw;
                    float f1 = this.player.pitch;

                    this.player.vehicle.i();
                    d1 = this.player.locX;
                    d2 = this.player.locY;
                    d3 = this.player.locZ;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (packet10flying.hasLook) {
                        f = packet10flying.yaw;
                        f1 = packet10flying.pitch;
                    }

                    if (packet10flying.h && packet10flying.y == -999.0D && packet10flying.stance == -999.0D) {
                        d5 = packet10flying.x;
                        d4 = packet10flying.z;
                    }

                    this.player.onGround = packet10flying.g;
                    this.player.a(true);
                    this.player.move(d5, 0.0D, d4);
                    this.player.setLocation(d1, d2, d3, f, f1);
                    this.player.motX = d5;
                    this.player.motZ = d4;
                    if (this.player.vehicle != null) {
                        worldserver.vehicleEnteredWorld(this.player.vehicle, true);
                    }

                    if (this.player.vehicle != null) {
                        this.player.vehicle.i();
                    }

                    this.minecraftServer.serverConfigurationManager.d(this.player);
                    this.x = this.player.locX;
                    this.y = this.player.locY;
                    this.z = this.player.locZ;
                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                if (this.player.isSleeping()) {
                    this.player.a(true);
                    this.player.setLocation(this.x, this.y, this.z, this.player.yaw, this.player.pitch);
                    worldserver.playerJoinedWorld(this.player);
                    return;
                }

                d0 = this.player.locY;
                this.x = this.player.locX;
                this.y = this.player.locY;
                this.z = this.player.locZ;
                d1 = this.player.locX;
                d2 = this.player.locY;
                d3 = this.player.locZ;
                float f2 = this.player.yaw;
                float f3 = this.player.pitch;

                if (packet10flying.h && packet10flying.y == -999.0D && packet10flying.stance == -999.0D) {
                    packet10flying.h = false;
                }

                if (packet10flying.h) {
                    d1 = packet10flying.x;
                    d2 = packet10flying.y;
                    d3 = packet10flying.z;
                    d4 = packet10flying.stance - packet10flying.y;
                    if (!this.player.isSleeping() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.disconnect("Illegal stance");
                        a.warning(this.player.name + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(packet10flying.x) > 3.2E7D || Math.abs(packet10flying.z) > 3.2E7D) {
                        this.disconnect("Illegal position");
                        return;
                    }
                }

                if (packet10flying.hasLook) {
                    f2 = packet10flying.yaw;
                    f3 = packet10flying.pitch;
                }

                this.player.a(true);
                this.player.bO = 0.0F;
                this.player.setLocation(this.x, this.y, this.z, f2, f3);
                if (!this.checkMovement) {
                    return;
                }

                d4 = d1 - this.player.locX;
                double d6 = d2 - this.player.locY;
                double d7 = d3 - this.player.locZ;

                if (this.player.onGround && !packet10flying.g && d6 > 0.0D) {
                    this.player.c(0.2F);
                }

                this.player.move(d4, d6, d7);
                this.player.onGround = packet10flying.g;
                this.player.b(d4, d6, d7);

                this.player.setLocation(d1, d2, d3, f2, f3);

                this.player.onGround = packet10flying.g;
                this.minecraftServer.serverConfigurationManager.d(this.player);
                if (this.player.itemInWorldManager.b()) {
                    return;
                }
                this.player.b(this.player.locY - d0, packet10flying.g);
            }
        }
    }
}
