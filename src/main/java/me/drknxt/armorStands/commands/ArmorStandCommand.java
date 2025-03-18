package me.drknxt.armorStands.commands;

import me.drknxt.armorStands.ArmorStands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStands plugin;

    public ArmorStandCommand(ArmorStands plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {
            plugin.openASMenu(p);
        }

        return true;
    }
}
