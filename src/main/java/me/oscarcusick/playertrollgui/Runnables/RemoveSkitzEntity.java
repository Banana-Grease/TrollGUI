package me.oscarcusick.playertrollgui.Runnables;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.UUID;

public class RemoveSkitzEntity extends BukkitRunnable {
    Plugin PluginInstance;
    Player TargetPlayer;
    UUID EntityID;
    Location EntityLoc;

    public RemoveSkitzEntity(Plugin ParsedPluginInstance, Player ParsedTargetPlayer, UUID ParsedEntityID, Location ParsedEntityLoc) {
        PluginInstance = ParsedPluginInstance;
        TargetPlayer = ParsedTargetPlayer;
        EntityID = ParsedEntityID;
        EntityLoc = ParsedEntityLoc;
    }

    public void run() {
        TargetPlayer.kickPlayer("Don't Tell Anyone, But They're Watching You");
    }
}