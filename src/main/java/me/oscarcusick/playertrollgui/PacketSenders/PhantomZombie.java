package me.oscarcusick.playertrollgui.PacketSenders;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import me.oscarcusick.playertrollgui.Runnables.RemoveSkitzEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.lang.annotation.Target;
import java.util.UUID;

public class PhantomZombie {

    public void SpawnPhantomZmombie(Plugin PluginInsance, Player TargetPlayer) {
        UUID EntityUUID = UUID.randomUUID();
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getIntegers().write(0, 54);
        packet.getEntityTypeModifier().write(0, EntityType.PLAYER);
        packet.getUUIDs().write(0, TargetPlayer.getUniqueId()); // entity ID
        Location SpawnLoc = TargetPlayer.getLocation();
        SpawnLoc = (TargetPlayer.getEyeLocation().add(TargetPlayer.getLocation().getDirection().multiply(15))); // gets the block 15 infront of player's head (stolen code)
        SpawnLoc.setY(PluginInsance.getServer().getWorld(TargetPlayer.getWorld().getUID()).getHighestBlockAt(SpawnLoc.getBlockX(), SpawnLoc.getBlockZ()).getY() + 1); // sets the Y value to the highest block at SpawnLoc + 1 to 'spawn' entity
        SpawnLoc.setYaw(TargetPlayer.getLocation().getYaw() + 180); // sets the yaw to the opposite of target player so the entity faces the target player
        packet.getDoubles().write(0, SpawnLoc.getX());
        packet.getDoubles().write(1, SpawnLoc.getY());
        packet.getDoubles().write(2, SpawnLoc.getZ());
        packet.getBytes().write(2, (byte) (SpawnLoc.getYaw() * 256.0F / 360.0F)); // write the yaw. The math is used to get "Real Yaw". The second byte is the yaw parameter
        manager.sendServerPacket(TargetPlayer, packet); // send that faggot
        TargetPlayer.getWorld().strikeLightning(SpawnLoc);
        BukkitTask RemoveLater = new RemoveSkitzEntity(PluginInsance, TargetPlayer, EntityUUID, SpawnLoc).runTaskLater(PluginInsance, 20*2);
    }
}
