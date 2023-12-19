package me.oscarcusick.playertrollgui.GUI;

import me.oscarcusick.playertrollgui.Utility.GUIUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class LengthSelectGUI {
    Plugin PluginInstance;
    Player User, TPlayer;
    String GUIName;
    public LengthSelectGUI(Plugin ParsedPluginInstance, Player ParsedUser, Player ParsedTargetPlayer, String ParsedGUIName) {
        PluginInstance = ParsedPluginInstance;
        User = ParsedUser;
        TPlayer = ParsedTargetPlayer;
        GUIName = ParsedGUIName;
    }

    public void OpenGUI() {
        GUIUtility GUtil = new GUIUtility(PluginInstance);
        Inventory EarRapeSelectGUI = Bukkit.createInventory(User, 9, ChatColor.RED + GUIName);
        ArrayList<String> GUIButtonLore = new ArrayList<>(); // the lore arrlist for all the buttons / items in the GUI

        //Create emptyslot & set all slots to it
        ItemStack EmptySlot = GUtil.GenerateGUIButton(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", new ArrayList<>());
        for (int i = 0; i < EarRapeSelectGUI.getSize(); i++) {
            EarRapeSelectGUI.setItem(i, EmptySlot);
        }

        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Targeted Player"); // Target Player Head
        EarRapeSelectGUI.setItem(0, GUtil.GenerateGUIButton(Material.PLAYER_HEAD, TPlayer.getDisplayName(), GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Click This To Close The GUI!"); // Exit Button
        EarRapeSelectGUI.setItem(8, GUtil.GenerateGUIButton(Material.BARRIER, ChatColor.BOLD + "" + ChatColor.RED + "Quit Menu", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Very Short Duration");
        GUIButtonLore.add(ChatColor.DARK_PURPLE + "5 Seconds");
        EarRapeSelectGUI.setItem(2, GUtil.GenerateGUIButton(Material.IRON_NUGGET, ChatColor.GREEN + "Very Short", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Short Duration");
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "15 Seconds");
        EarRapeSelectGUI.setItem(3, GUtil.GenerateGUIButton(Material.RAW_IRON, ChatColor.DARK_GREEN + "Short", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Medium Duration");
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "30 Seconds");
        EarRapeSelectGUI.setItem(4, GUtil.GenerateGUIButton(Material.IRON_INGOT, ChatColor.GOLD + "Medium", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Long Duration");
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "45 Seconds");
        EarRapeSelectGUI.setItem(5, GUtil.GenerateGUIButton(Material.RAW_IRON_BLOCK, ChatColor.RED + "Long", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Very Long Duration");
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "60 Seconds");
        EarRapeSelectGUI.setItem(6, GUtil.GenerateGUIButton(Material.IRON_BLOCK, ChatColor.DARK_RED + "Very Long", GUIButtonLore));

        User.openInventory(EarRapeSelectGUI);
    }
}
