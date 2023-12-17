package me.oscarcusick.playertrollgui;

import me.oscarcusick.playertrollgui.Commands.TrollGUICommandExecutor;
import me.oscarcusick.playertrollgui.GUIListeners.PunishmentSelectGUIListener;
import me.oscarcusick.playertrollgui.GeneralListeners.OnPlayerMoveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerTrollGUI extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("TrollGUI").setExecutor(new TrollGUICommandExecutor(this));
        getServer().getPluginManager().registerEvents(new PunishmentSelectGUIListener(this), this);

        // this event isnt used. meant to freeze player when Who let the dawgs out is called
        //getServer().getPluginManager().registerEvents(new OnPlayerMoveListener(this), this);
    }
}
