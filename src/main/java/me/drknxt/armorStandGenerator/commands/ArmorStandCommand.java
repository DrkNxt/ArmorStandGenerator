package me.drknxt.armorStandGenerator.commands;

import me.drknxt.armorStandGenerator.ArmorStandGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStandGenerator plugin;

    public ArmorStandCommand(ArmorStandGenerator plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                plugin.openASMenu(p);
            } else if (args.length == 1) {
                switch (args[0]) {
                    case "mainhand":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setItemInMainHand(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                    break;
                    case "offhand":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setItemInOffHand(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                        break;
                    case "head":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setHelmet(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                        break;
                    case "chest":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setChestplate(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                        break;
                    case "legs":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setLeggings(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                        break;
                    case "feet":
                        if (plugin.armorStands.get(p) != null) {
                            Objects.requireNonNull(plugin.armorStands.get(p).getEquipment()).setBoots(p.getInventory().getItemInMainHand());
                        }else {
                            p.sendMessage(ChatColor.RED + "You don't have an active armor stand!");
                        }
                        break;
                    default:
                            p.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.DARK_PURPLE + "/asg [mainhand | offhand | head | chest | legs | feet]");
                }
            } else {
                if (args[0].equals("setname")) {
                    if  (plugin.armorStands.get(p) != null){
                        StringBuilder name = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            name.append(args[i]).append(" ");
                        }
                        name.deleteCharAt(name.length() - 1);
                        plugin.armorStands.get(p).setCustomName(ChatColor.translateAlternateColorCodes('&', name.toString()));
                        plugin.armorStands.get(p).setCustomNameVisible(true);
                        p.sendMessage(ChatColor.GOLD + "Custom name set to: " + ChatColor.DARK_PURPLE + name);
                    }
                }else {
                    p.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.DARK_PURPLE + "/asg [mainhand | offhand | head | chest | legs | feet | setname] [name] ");
                }

            }
        }

        return true;
    }
}
