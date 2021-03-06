package com.oblicom.plugins.oblicomcore.command;

import java.util.List;
import java.util.Arrays;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.oblicom.plugins.oblicomcore.OblicomCore;

import com.oblicom.plugins.oblicomcore.entity.Citizen;

import org.bukkit.entity.Player;

/**
 *
 * @author nagib.kanaan
 */
public class ArrestCommand extends OblicomCommand {
    OblicomCore plugin;
    
    String requiredPermission = "oblicom.jail.jail";
    String withoutPermissionMessage = ChatColor.RED + "No Permission!";
    
    public ArrestCommand(OblicomCore plugin) {
        this.plugin = plugin;
    }
    
    public boolean validatedCommand(String command) {
        return command.equalsIgnoreCase("oblijail");
    }
    
    public boolean onOblicomCommand(CommandSender sender, Command command, String value, String[] params) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            if (!player.hasPermission(requiredPermission)) {
                player.sendMessage(withoutPermissionMessage);
                return true;
            }
        }
        
        if (params.length > 2) {
           sender.sendMessage(ChatColor.LIGHT_PURPLE + "Too many arguments!");
           return false;
        }
        
        if (params.length < 2) {
           sender.sendMessage(ChatColor.LIGHT_PURPLE + "Not enough arguments!");
           return false;
        }
        
        Player target = plugin.getServer().getPlayer(params[0]);
        if (target == null) {
           sender.sendMessage(ChatColor.DARK_RED +  params[0] + " is not online!");
           return true;
        }
        
        Citizen citizen = new Citizen(target);
        
        if (citizen.isArrested()) {
           sender.sendMessage(ChatColor.RED + "Player " + params[0] + " already in jail!");
           return true;
        }
        
        citizen.arrest(params[1]);
        
        sender.sendMessage(ChatColor.GREEN + "Player " + params[0] + " arrested.");
        
        return true;
    }
}