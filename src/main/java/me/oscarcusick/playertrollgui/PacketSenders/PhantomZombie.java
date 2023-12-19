package me.oscarcusick.playertrollgui.PacketSenders;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PhantomZombie {

    public void SpawnPhantomZmombie(Plugin PluginInsance, Player TargetPlayer) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getIntegers().write(0, 54);
        packet.getEntityTypeModifier().write(0, EntityType.ZOMBIE);
        packet.getDoubles().write(0, TargetPlayer.getLocation().getX());
        packet.getDoubles().write(1, TargetPlayer.getLocation().getY());
        packet.getDoubles().write(2, TargetPlayer.getLocation().getZ());
        manager.sendServerPacket(TargetPlayer, packet);
    }
}
