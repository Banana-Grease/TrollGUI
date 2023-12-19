package me.oscarcusick.playertrollgui.Runnables;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CreeperExplosionRunnable extends BukkitRunnable {
    Plugin PluginInstance;
    UUID PlayerUUID;
    public CreeperExplosionRunnable(Plugin ParsedPluginInstance, UUID ParsedPlayerUUID) {
        PluginInstance = ParsedPluginInstance;
        PlayerUUID = ParsedPlayerUUID;
    }

        public void run() {
            PluginInstance.getServer().getPlayer(PlayerUUID).getWorld().spawnParticle(Particle.EXPLOSION_HUGE, PluginInstance.getServer().getPlayer(PlayerUUID).getLocation(), 1);
            PluginInstance.getServer().getPlayer(PlayerUUID).getWorld().playSound(PluginInstance.getServer().getPlayer(PlayerUUID).getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            PluginInstance.getServer().getPlayer(PlayerUUID).sendHurtAnimation(0);
        }

}
