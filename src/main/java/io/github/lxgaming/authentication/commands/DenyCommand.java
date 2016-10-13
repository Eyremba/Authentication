package io.github.lxgaming.authentication.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class DenyCommand implements CommandExecutor {
	
	public int Delay =  Authentication.config.getInt("Authentication.Commands.DenyCommand.KickDelay");
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("deny") || cmd.getName().equalsIgnoreCase("adeny")&& sender instanceof Player) {
			
			Player player = (Player) sender;
			String name = player.getPlayer().getName();
			Authentication.instance.setPlayerDeny(player);
			
			List<String> list = Authentication.database.getStringList("Authentication.Database");
			list.remove(name);
			Authentication.database.set("Authentication.Database", list);
			try {
				Authentication.database.save(Authentication.databaseFile);
				Authentication.instance.getLogger().info(name + " Was removed from the Database");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			Authentication.instance.reloadConfig();
			
			if (Authentication.config.getBoolean("Authentication.Commands.DenyCommand.Kick") == true) {
				if (Authentication.config.getBoolean("Authentication.Commands.DenyCommand.MessageBeforeKick") == true) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands.DenyCommand")));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Authentication.instance, new Runnable() {
						public void run() {
							player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands.Kick")));
						}
					}, Delay);
					return true;
				} else {
					player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands..Kick")));
					return true;
				}
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Commands.DenyCommand")));
			}
			return true;
		}
		return false;
	}

}