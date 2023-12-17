package me.oscarcusick.playertrollgui.Commands;

import me.oscarcusick.playertrollgui.GUI.PunishmentSelectGUI;
import me.oscarcusick.playertrollgui.Utility.GUIUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

public class TrollGUICommandExecutor implements CommandExecutor {
    Plugin PluginInstance;
    public TrollGUICommandExecutor(Plugin ParsedPluginInstance) {
        PluginInstance = ParsedPluginInstance;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player User = (Player)commandSender;
            switch (strings.length) {
                case 0: // No Player Name Parsed
                    //unfinished due to player heads
                    // need to figure those bastards out first
                    return false;

                case 1: // 1 Argument parsed, Should be player name
                    Player TargetPlayer = PluginInstance.getServer().getPlayer(strings[0]);
                    GUIUtility GUtil = new GUIUtility(PluginInstance);
                    if (PluginInstance.getServer().getOnlinePlayers().contains(TargetPlayer)) { // target player has been 'located' and targeted
                        // open troll selection GUI
                        PunishmentSelectGUI PSGUI = new PunishmentSelectGUI(PluginInstance, User, TargetPlayer);
                        PSGUI.OpenGUI();
                        return true;
                    }else {
                        User.sendMessage("This Player either doesnt exist, or isn't online :'(");
                        return false;
                    }
                default:
                    return false;
            }


        }
        return false;
    }
}
