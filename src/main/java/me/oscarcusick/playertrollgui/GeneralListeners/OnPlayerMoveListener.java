package me.oscarcusick.playertrollgui.GeneralListeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class OnPlayerMoveListener implements Listener {
    Plugin PluginInstance;
    public OnPlayerMoveListener(Plugin ParsedPluginInstance) {
        PluginInstance = ParsedPluginInstance;
    }

    @EventHandler
    public void OnPlayerMoveListener(PlayerMoveEvent event) {
        Player User = (Player)event.getPlayer();
        if (User.getPersistentDataContainer().has(new NamespacedKey(PluginInstance, "PlayerFrozen"), PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
}
