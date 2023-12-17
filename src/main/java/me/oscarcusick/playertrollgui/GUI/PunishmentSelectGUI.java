package me.oscarcusick.playertrollgui.GUI;

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

public class PunishmentSelectGUI {
    Plugin PluginInstance;
    Player User, TPlayer;
    public PunishmentSelectGUI(Plugin ParsedPluginInstance, Player ParsedUser, Player ParsedTargetPlayer) {
        PluginInstance = ParsedPluginInstance;
        User = ParsedUser;
        TPlayer = ParsedTargetPlayer;
    }

    public void OpenGUI() {
        GUIUtility GUtil = new GUIUtility(PluginInstance);

        int PSGUISize = 9*4; // this will never change. just so i can do math on how big the PSGUI is
        Inventory PunishmentSelectGUI = Bukkit.createInventory(User, PSGUISize, ChatColor.RED + "Troll Menu - Select Punishment");
        ArrayList<String> GUIButtonLore = new ArrayList<>(); // the lore arrlist for all the buttons / items in the GUI

        // creating itemstacks and setting them

        //Create emptyslot
        ItemStack EmptySlot = GUtil.GenerateGUIButton(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", new ArrayList<>());

        // set the outermost slots to greyempty for asthetic
        ItemStack GrayEmptySlot = GUtil.GenerateGUIButton(Material.GRAY_STAINED_GLASS_PANE, " ", new ArrayList<>());

        //set all unset slots to EmptySlot or grey empty slot if its an outer most slot
        for (int i = 0; i < PunishmentSelectGUI.getSize(); i++) {
            if (i < 9 || (i > 9*((PSGUISize/9)-1)-1) || i%9==0) {
                PunishmentSelectGUI.setItem(i, GrayEmptySlot);
            } else {
                PunishmentSelectGUI.setItem(i, EmptySlot);
            }
        }
        PunishmentSelectGUI.setItem(17, GrayEmptySlot); // these two are here becuase the left side is too hard. everything else autoconfigures to any GUI Size.
        PunishmentSelectGUI.setItem(26, GrayEmptySlot);

        // override empty slots with other items

        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Click This To Close The GUI!");// Exit Button
        PunishmentSelectGUI.setItem(8, GUtil.GenerateGUIButton(Material.BARRIER, ChatColor.BOLD + "" + ChatColor.RED + "Quit Menu", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Kill The Selected Player"); // kill TPlayer
        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Instantly");
        PunishmentSelectGUI.setItem(1+9, GUtil.GenerateGUIButton(Material.SKELETON_SKULL,ChatColor.RED + "Skank Em'", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Summon Explosion At Player"); // explode player
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Opens Up Menu With Power Setting");
        PunishmentSelectGUI.setItem(2+9, GUtil.GenerateGUIButton(Material.TNT,ChatColor.RED + "It Was A Creeper Bro!", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Summon Lightning On Player"); // smite player
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Doesn't Hurt Player, Plays Fake Hurt Animation");
        PunishmentSelectGUI.setItem(3+9, GUtil.GenerateGUIButton(Material.BLAZE_ROD,ChatColor.GREEN + "Thor?!?", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Spawns 5 Hostile Wolfs On Player"); // spawns 5 hostile dogs
        GUIButtonLore.remove(1);
        PunishmentSelectGUI.setItem(4+9, GUtil.GenerateGUIButton(Material.WOLF_SPAWN_EGG,ChatColor.GOLD + "Who Let the Dawgs Out?", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Turns Player Into Lester From \"GTA 5\""); // Lesterify
        GUIButtonLore.add(ChatColor.DARK_PURPLE + "Each Click Adds 10 Seconds Of Lester Time");
        PunishmentSelectGUI.setItem(5+9, GUtil.GenerateGUIButton(Material.PIGLIN_HEAD,ChatColor.GREEN + "Lesterify", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Sends Player A Message Saying the have OP, They Don't");// fake operator
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "When They Realise Their Face Will Look Like The Icon");
        PunishmentSelectGUI.setItem(6+9, GUtil.GenerateGUIButton(Material.PUFFERFISH,ChatColor.GREEN + "Hell Yeah, OP!", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Fake Creeper"); // fake creeper
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Fake Explosion Sound And Particles Behind Player");
        PunishmentSelectGUI.setItem(7+9, GUtil.GenerateGUIButton(Material.GUNPOWDER,ChatColor.GREEN + "Hisss....", GUIButtonLore));

        // SECOND ROW OF ICONS

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Rotate Player 180 degrees"); // rotate player 180 degrees
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Plays Bell Sound, Why Not?");
        PunishmentSelectGUI.setItem(1+9*2, GUtil.GenerateGUIButton(Material.MANGROVE_DOOR,ChatColor.GREEN + "No You Don't", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Bans Player For 1 Second");
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "I See No Harm");
        PunishmentSelectGUI.setItem(2+9*2, GUtil.GenerateGUIButton(Material.PAPER,ChatColor.GREEN + "Good Bye!", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Kicks From Server"); // send a fake crash to the player
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Looks Like Java Crapped It's Pants");
        PunishmentSelectGUI.setItem(3+9*2, GUtil.GenerateGUIButton(Material.BLAZE_POWDER,ChatColor.GREEN + "We Love Java Edition!", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Ear Rapes Player"); // ear rapes player for selected amount of time in a child GUI
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Opens Up Menu With Duration Settings");
        PunishmentSelectGUI.setItem(4+9*2, GUtil.GenerateGUIButton(Material.SCULK_SENSOR,ChatColor.GREEN + "Shh! Did You Hear That?", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Spams Player With The Damage Animation"); // spams the damage animation and the hurt sound effect
        GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Opens Up Menu With Duration Settings");
        PunishmentSelectGUI.setItem(5+9*2, GUtil.GenerateGUIButton(Material.REDSTONE,ChatColor.GREEN + "Ouchie! Stubbed My Toe", GUIButtonLore));

        //GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "All Empty Positions Become"); // fills empty slots with dead bushes
        //GUIButtonLore.set(1, ChatColor.DARK_PURPLE + "Dead Bushes");
        //PunishmentSelectGUI.setItem(6+9*2, GUtil.GenerateGUIButton(Material.DEAD_BUSH,ChatColor.GREEN + "Thx Bro", GUIButtonLore));

        GUIButtonLore.set(0, ChatColor.DARK_PURPLE + "Targeted Player"); // Target Player Head, the head name is how the target player gets "extracted" later through the listener
        GUIButtonLore.remove(1);
        PunishmentSelectGUI.setItem(0, GUtil.GenerateGUIButton(Material.PLAYER_HEAD, TPlayer.getDisplayName(), GUIButtonLore));

        User.openInventory(PunishmentSelectGUI);
    }
}
