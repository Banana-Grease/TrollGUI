package me.oscarcusick.playertrollgui.Utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class GUIUtility {
    Plugin PluginInstance;
    Inventory InventoryInstance;
    public GUIUtility(Plugin ParsedPluginInstance) {
        PluginInstance = ParsedPluginInstance;
    }

    public ItemStack GenerateGUIButton(Material ItemType, String Name, ArrayList<String> Lore) {
        ItemStack ResultButton = new ItemStack(ItemType);
        ItemMeta ResultButtonMeta = ResultButton.getItemMeta();
        ResultButtonMeta.setDisplayName(Name);
        ResultButtonMeta.setLore(Lore);
        ResultButton.setItemMeta(ResultButtonMeta);
        return ResultButton;
    }
}
