package com.orcacraft.smallpetsexpansion.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;

import com.orcacraft.smallpetsexpansion.items.RareCandy;

public class GiveRareCandy implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!sender.hasPermission("smallpetsexpansion.admin") || sender.isOp()
				|| sender instanceof ConsoleCommandSender) {

			sender.sendMessage(
					ChatColor.GRAY + "(" + ChatColor.YELLOW + "!" + ChatColor.GRAY + ") Unrecognized command.");
		}

		if (command.getName().equalsIgnoreCase("giverarecandy")) {

			if (args.length < 2) {
				usage(sender);
				return true;
			}

			if (Bukkit.getServer().getPlayerExact(args[0]) != null) {

				if (Bukkit.getServer().getPlayerExact(args[0]).getInventory().firstEmpty() == -1) {
					sender.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + "!" + ChatColor.GRAY
							+ ") Player's inventory is full.");
					return true;
				}

				final ItemStack candy = RareCandy.getRareCandy();
				candy.setAmount(Integer.valueOf(args[1]));

				Bukkit.getServer().getPlayerExact(args[0]).getInventory().addItem(candy);
				Bukkit.getServer().getPlayerExact(args[0]).sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + "!"
						+ ChatColor.GRAY + ") " + sender.getName() + " gave you " + args[1] + " rare candy!");

			} else {
				sender.sendMessage(
						ChatColor.GRAY + "(" + ChatColor.YELLOW + "!" + ChatColor.GRAY + ") Player not found.");
			}
		}

		return true;
	}

	private void usage(CommandSender sender) {

		sender.sendMessage(
				ChatColor.GRAY + "(" + ChatColor.YELLOW + "!" + ChatColor.GRAY + ") /giverarecandy {player} {amount}");
	}

}
