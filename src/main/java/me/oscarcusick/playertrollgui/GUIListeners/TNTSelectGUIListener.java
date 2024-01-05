package me.oscarcusick.playertrollgui.GUIListeners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class TNTSelectGUIListener implements Listener {
    Plugin PluginInstance;
    public TNTSelectGUIListener(Plugin ParsedPluginInstance) {
        PluginInstance = ParsedPluginInstance;
    }
}
