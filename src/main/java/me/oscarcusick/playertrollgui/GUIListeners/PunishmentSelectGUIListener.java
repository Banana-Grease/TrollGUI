package me.oscarcusick.playertrollgui.GUIListeners;

import me.oscarcusick.playertrollgui.GUI.LengthSelectGUI;
import me.oscarcusick.playertrollgui.GUI.TNTSelectGUI;
import me.oscarcusick.playertrollgui.PacketSenders.CrashClient;
import me.oscarcusick.playertrollgui.PacketSenders.PhantomZombie;
import me.oscarcusick.playertrollgui.PlayerTrollGUI;
import me.oscarcusick.playertrollgui.Runnables.CreeperExplosionRunnable;
import me.oscarcusick.playertrollgui.Runnables.DamageSpamRunnables;
import me.oscarcusick.playertrollgui.Runnables.EarRapeRunnable;
import me.oscarcusick.playertrollgui.Utility.GUIUtility;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

public class PunishmentSelectGUIListener implements Listener {
    Plugin PluginInstance;
    public PunishmentSelectGUIListener(Plugin ParsedPluginInstance) {
        PluginInstance = ParsedPluginInstance;
    }

    @EventHandler
    public void OnPunishmentSelectGuiClick(InventoryClickEvent event) {
        // detect which menu user is in. Main or a sub menu
        Player User = (Player)event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED +"Troll Menu - Select Punishment")) {
            event.setCancelled(true); // stop them stealing GUI shit
            Player TPlayer = PluginInstance.getServer().getPlayer(event.getClickedInventory().getItem(0).getItemMeta().getDisplayName()); // get player from the head in GUI Slot 0
            GUIUtility GUtil = new GUIUtility(PluginInstance);
            switch (event.getCurrentItem().getType()) { // throws error if "air" is clicked
                case BARRIER:
                    event.getWhoClicked().closeInventory();
                    return;
                case SKELETON_SKULL:
                    TPlayer.setHealth(0);
                    return;
                case BLAZE_ROD:
                    TPlayer.getWorld().strikeLightningEffect(TPlayer.getLocation());
                    TPlayer.sendHurtAnimation(0);
                    TPlayer.playSound(TPlayer.getLocation(),Sound.ENTITY_PLAYER_HURT, 1, 1);
                    return;
                case WOLF_SPAWN_EGG:
                    Wolf DawgInstance; // remember to remove later
                    for (int i = 0; i < 5; i++) {
                        DawgInstance = (Wolf)TPlayer.getWorld().spawnEntity(TPlayer.getLocation(), EntityType.WOLF, false);
                        DawgInstance.setAngry(true);
                        DawgInstance.setTarget(TPlayer);
                    }
                    return;
                case PIGLIN_HEAD:
                    int LesterBlindTime = 0, LesterMineTime = 0, LesterSlowTime = 0, LesterWeaknessTime = 0, LesterNausiaTime = 0, LesterDarknessTime = 0;
                    if (TPlayer.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                        LesterBlindTime = TPlayer.getPotionEffect(PotionEffectType.BLINDNESS).getDuration();
                    } if (TPlayer.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
                        LesterMineTime = TPlayer.getPotionEffect(PotionEffectType.SLOW_DIGGING).getDuration();
                    } if (TPlayer.hasPotionEffect(PotionEffectType.SLOW)) {
                    LesterSlowTime = TPlayer.getPotionEffect(PotionEffectType.SLOW).getDuration();
                    } if (TPlayer.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                    LesterWeaknessTime = TPlayer.getPotionEffect(PotionEffectType.WEAKNESS).getDuration();
                    } if (TPlayer.hasPotionEffect(PotionEffectType.CONFUSION)) {
                    LesterNausiaTime = TPlayer.getPotionEffect(PotionEffectType.CONFUSION).getDuration();
                    } if (TPlayer.hasPotionEffect(PotionEffectType.DARKNESS)) {
                    LesterDarknessTime = TPlayer.getPotionEffect(PotionEffectType.DARKNESS).getDuration();
                    }
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (10*20)+LesterBlindTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (10*20)+LesterMineTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (10*20)+LesterSlowTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (10*20)+LesterWeaknessTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (10*20)+LesterBlindTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (10*20)+LesterNausiaTime, 100, false, false, false));
                    TPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, (10*20)+LesterDarknessTime, 100, false, false, false));
                    return;
                case PUFFERFISH:
                    TPlayer.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[Server: Made " + TPlayer.getDisplayName() + " a server operator]");
                    return;
                case GUNPOWDER:
                    TPlayer.playSound(TPlayer, Sound.ENTITY_CREEPER_PRIMED, 1, 1);
                    BukkitTask CreeperBoom = new CreeperExplosionRunnable(PluginInstance, TPlayer.getUniqueId()).runTaskLater(PluginInstance, 30); // plays an explosion sound later
                    return;
                case MANGROVE_DOOR:
                    Location NewLoc = TPlayer.getLocation();
                    NewLoc.setYaw(NewLoc.getYaw()+180);
                    TPlayer.teleport(NewLoc);
                    TPlayer.getWorld().playSound(TPlayer, Sound.BLOCK_BELL_USE, 1, 1);
                    return;
                case PAPER:
                    Duration Dur = Duration.ofSeconds(1);
                    TPlayer.ban("You are banned from this server", Dur, "Get Memed", true);
                    return;
                case BLAZE_POWDER:
                    TPlayer.kickPlayer("Internal Exception: java.io.IOException: An existing connection was forcibly closed by the remote host");
                    return;
                //case DEAD_BUSH:
                //    PlayerInventory PlayerInv = TPlayer.getInventory();
                //    PlayerInv.
                //    return;
                case BLUE_ICE:
                    //particle bubble_column_up ~ ~ ~ 8 8 8 1 900000000 -> this is the equivalent of how we freeze the game. Safe because it sends a particle packet to ONLY that client
                    // The reason why i use ProtocolLib for this is because i know exactly who will recieve it. I don't really want to risk the player.spawnparticle becuase ive seen mixed results
                    CrashClient CC = new CrashClient();
                    CC.AttemptCrashClient(PluginInstance, TPlayer);
                    return;
                case ZOMBIE_HEAD:
                    PhantomZombie PZ = new PhantomZombie();
                    PZ.SpawnPhantomZmombie(PluginInstance, TPlayer);
                    return;
                case SCULK_SENSOR:
                    LengthSelectGUI ERSGUI = new LengthSelectGUI(PluginInstance, User, TPlayer, "Troll Menu - Ear Rape Length");
                    ERSGUI.OpenGUI();
                    return;
                case REDSTONE:
                    LengthSelectGUI DSLGUI = new LengthSelectGUI(PluginInstance, User, TPlayer, "Troll Menu - Hurt Spam Length");
                    DSLGUI.OpenGUI();
                    return;
                case TNT:
                    TNTSelectGUI TNTSGUI = new TNTSelectGUI(PluginInstance, User, TPlayer, "Troll Menu - TNT Options");
                    TNTSGUI.OpenGUI();
                    return;
            }
        }
        else if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Troll Menu - Ear Rape Length")) {
            event.setCancelled(true); // stop them stealing GUI shit
            Player TPlayer = PluginInstance.getServer().getPlayer(event.getClickedInventory().getItem(0).getItemMeta().getDisplayName()); // get player from the head in position 0
            switch (event.getCurrentItem().getType()) {
                case BARRIER:
                    event.getWhoClicked().closeInventory();
                    return;
                case LIGHT_GRAY_STAINED_GLASS_PANE:
                    // do nothing
                    return;
                case PLAYER_HEAD:
                    // do nothing
                    return;
                default:
                    String SpamTimeSecs = event.getCurrentItem().getItemMeta().getLore().get(1);
                    SpamTimeSecs = SpamTimeSecs.replace(" Seconds", "");
                    SpamTimeSecs = SpamTimeSecs.replace("§5", "");
                    BukkitTask ERTask = new EarRapeRunnable(PluginInstance, TPlayer.getUniqueId(), Integer.parseInt(SpamTimeSecs)*10).runTaskTimer(PluginInstance, 0, 2);
                    return;
            }
        }
        else if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Troll Menu - Hurt Spam Length")) {
            event.setCancelled(true); // stop them stealing GUI shit
            Player TPlayer = PluginInstance.getServer().getPlayer(event.getClickedInventory().getItem(0).getItemMeta().getDisplayName()); // get player from the head in position 0
            switch (event.getCurrentItem().getType()) {
                case BARRIER:
                    event.getWhoClicked().closeInventory();
                    return;
                case LIGHT_GRAY_STAINED_GLASS_PANE:
                    // do nothing
                    return;
                case PLAYER_HEAD:
                    // do nothing
                    return;
                default:
                    String SpamTimeSecs = event.getCurrentItem().getItemMeta().getLore().get(1);
                    SpamTimeSecs = SpamTimeSecs.replace(" Seconds", "");
                    SpamTimeSecs = SpamTimeSecs.replace("§5", "");
                    BukkitTask DSTask = new DamageSpamRunnables(PluginInstance, TPlayer.getUniqueId(), Integer.parseInt(SpamTimeSecs)*2).runTaskTimer(PluginInstance, 0, 10);
                    return;
            }
        }
        // TNT option sub menu
        else if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Troll Menu - TNT Options")) {
            event.setCancelled(true); // stop them stealing GUI shit
            Player TPlayer = PluginInstance.getServer().getPlayer(event.getClickedInventory().getItem(0).getItemMeta().getDisplayName()); // get player from the head in position 0
            World TWorld = TPlayer.getWorld();
            switch (event.getCurrentItem().getType()) {
                case BARRIER: // cancel explostion
                    event.getWhoClicked().closeInventory();
                    return;
                case TNT: //spawn explosion. NOTE: this will be triggered for all 3 types however
                    ItemMeta ClickedItemMeta = event.getCurrentItem().getItemMeta();
                    if (ClickedItemMeta.getLore().get(1).contains("Low")) { // detecting which word is in second line of lore. (Low, Medium, High)
                        TWorld.createExplosion(TPlayer.getLocation(), 5);
                        event.getWhoClicked().closeInventory();
                    } else if (ClickedItemMeta.getLore().get(1).contains("Medium")) { // is medium?
                        TWorld.createExplosion(TPlayer.getLocation(), 30);
                        event.getWhoClicked().closeInventory();
                    }else if (ClickedItemMeta.getLore().get(1).contains("High")) { // is high?
                        TWorld.createExplosion(TPlayer.getLocation(), 100);
                        event.getWhoClicked().closeInventory();
                    }
                    return;
            }
        }
    }
}
