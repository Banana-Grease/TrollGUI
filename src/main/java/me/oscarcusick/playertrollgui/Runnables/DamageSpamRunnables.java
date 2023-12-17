package me.oscarcusick.playertrollgui.Runnables;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class DamageSpamRunnables extends BukkitRunnable {
    Plugin PluginInstance;
    int EndCounter;
    UUID PlayerUUID;
    public DamageSpamRunnables (Plugin ParsedPluginInstance, UUID ParsedPlayerUUID, int ParsedEndCounter) {
        PluginInstance = ParsedPluginInstance;
        EndCounter = ParsedEndCounter;
        PlayerUUID = ParsedPlayerUUID;
    }

    public void run() {
        if (EndCounter > 0) {
            PluginInstance.getServer().getPlayer(PlayerUUID).sendHurtAnimation(0);
            PluginInstance.getServer().getPlayer(PlayerUUID).playSound(PluginInstance.getServer().getPlayer(PlayerUUID), Sound.ENTITY_PLAYER_HURT, 1,1);
            EndCounter--;
        } else {
            this.cancel();
        }
    }
}
