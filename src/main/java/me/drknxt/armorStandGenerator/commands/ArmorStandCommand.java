package me.drknxt.armorStandGenerator.commands;

import me.drknxt.armorStandGenerator.ArmorStandGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStandGenerator plugin;

    public ArmorStandCommand(ArmorStandGenerator plugin) {
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
