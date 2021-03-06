package com.oblicom.plugins.oblicomcore.command;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.oblicom.plugins.oblicomcore.OblicomCore;

import org.bukkit.entity.Player;

/**
 *
 * @author nagib.kanaan
 */
public class SetjailCommand extends OblicomCommand {
    OblicomCore plugin;
    
    String requiredPermission = "oblicom.jail.set";
    String withoutPermissionMessage = ChatColor.RED + "No Permission!";
    
    public SetjailCommand(OblicomCore plugin) {
        this.plugin = plugin;
    }
    
    public boolean validatedCommand(String command) {
        return command.equalsIgnoreCase("oblisetjail");
    }
    
    public boolean onOblicomCommand(CommandSender sender, Command command, String value, String[] params) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(requiredPermission)) {
                sender.sendMessage(withoutPermissionMessage);
                return true;
            }
            
            if (params.length > 0) {
               sender.sendMessage(ChatColor.LIGHT_PURPLE + "This command takes no arguments!");
               return false;
            }            
            
            if (OblicomCore.world.getJail().setLocation((Player) sender)) {
                sender.sendMessage(ChatColor.GREEN + "Set the jail location to your position!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Error to set the Jail location!");
                return true;
            }
        }
        
        sender.sendMessage(ChatColor.RED + "You must be a player!");
        return true;
    }
}