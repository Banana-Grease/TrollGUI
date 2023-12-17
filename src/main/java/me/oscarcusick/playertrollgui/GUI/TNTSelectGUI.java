package me.oscarcusick.playertrollgui.GUI;

import me.oscarcusick.playertrollgui.Utility.GUIUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class TNTSelectGUI {
    Plugin PluginInstance;
    Player User, TPlayer;
    String GUIName;
    public TNTSelectGUI(Plugin ParsedPluginInstance, Player ParsedUser, Player ParsedTargetPlayer, String ParsedGUIName) {
        PluginInstance = ParsedPluginInstance;
        User = ParsedUser;
        TPlayer = ParsedTargetPlayer;
        GUIName = ParsedGUIName;
    }

    public void OpenGUI() {
        GUIUtility GUtil = new GUIUtility(PluginInstance);
        Inventory TNTSelectGUI = Bukkit.createInventory(User, 9, ChatColor.RED + GUIName);
        ArrayList<String> GUIButtonLore = new ArrayList<>(); // the lore arrlist for all the buttons / items in the GUI

        //Create emptyslot & set all slots to it
        ItemStack EmptySlot = GUtil.GenerateGUIButton(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", new ArrayList<>());
        for (int i = 0; i < TNTSelectGUI.getSize(); i++) {
            TNTSelectGUI.setItem(i, EmptySlot);
        }

        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Targeted Player"); // Target Player Head
        TNTSelectGUI.setItem(0, GUtil.GenerateGUIButton(Material.PLAYER_HEAD, TPlayer.getDisplayName(), GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Click This To Close The GUI!"); // Exit Button
        TNTSelectGUI.setItem(8, GUtil.GenerateGUIButton(Material.BARRIER, ChatColor.BOLD + "" + ChatColor.RED + "Quit Menu", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Summon Explosion At Player"); // First TNT Option
        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Power Setting " + ChatColor.GREEN + "Low");
        TNTSelectGUI.setItem(3, GUtil.GenerateGUIButton(Material.TNT, ChatColor.RED + "Size:" + ChatColor.GREEN + " 5", GUIButtonLore));

        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Power Setting " + ChatColor.YELLOW + "Medium"); // Second TNT Option
        TNTSelectGUI.setItem(4, GUtil.GenerateGUIButton(Material.TNT, ChatColor.RED + "Size:" + ChatColor.YELLOW + " 30", GUIButtonLore));

        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Power Setting " + ChatColor.RED + "High"); // Second TNT Option
        TNTSelectGUI.setItem(5, GUtil.GenerateGUIButton(Material.TNT, ChatColor.RED + "Size:" + ChatColor.RED + " 100", GUIButtonLore));

        User.openInventory(TNTSelectGUI);
    }
}
