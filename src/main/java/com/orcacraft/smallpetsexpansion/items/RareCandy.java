package com.orcacraft.smallpetsexpansion.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.orcacraft.smallpetsexpansion.Main;

import it.smallcode.smallpets.core.SmallPetsCommons;
import it.smallcode.smallpets.core.pets.Pet;

/**
 * The Class RareCandy.
 */
public class RareCandy implements Listener {

	/** The Constant rareCandy. */
	final static ItemStack rareCandy = new ItemStack(Material.MAGMA_CREAM, 1);

	/** The Constant rareCandyMeta. */
	final static ItemMeta rareCandyMeta = rareCandy.getItemMeta();

	/** The lore. */
	static List<String> lore = new ArrayList<>();

	/**
	 * Instantiates a new rare candy.
	 */
	public RareCandy() {

		rareCandyMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Rare Candy");

		lore.add(ChatColor.GRAY + "Right click with this to add a level to your currently equiped pet!");

		rareCandyMeta.setLore(lore);

		rareCandy.setItemMeta(rareCandyMeta);
	}

	/**
	 * Gets the rare candy.
	 *
	 * @return the rare candy
	 */
	public static ItemStack getRareCandy() {
		return rareCandy;
	}

	/**
	 * Gets the rare candy meta.
	 *
	 * @return the rare candy meta
	 */
	public static ItemMeta getRareCandyMeta() {
		return rareCandyMeta;
	}

	/**
	 * Gets the lore.
	 *
	 * @return the lore
	 */
	public static List<String> getLore() {
		return lore;
	}

	/**
	 * On usage of the rare candy.
	 *
	 * @param e
	 *            the PlayerInteractEvent
	 */
	@EventHandler
	public void onUsage(PlayerInteractEvent e) {

		final Player p = e.getPlayer();

		if (!e.getAction().equals(Action.RIGHT_CLICK_AIR))
			return;

		if (!p.getInventory().getItemInMainHand().isSimilar(rareCandy))
			return;

		final ItemStack candyStack = p.getInventory().getItemInMainHand();

		final Pet pet = SmallPetsCommons.getSmallPetsCommons().getUserManager().getUser(p.getUniqueId().toString())
				.getSelected();

		if (pet == null)
			return;

		if (pet.getLevel() == 100) {

			p.sendMessage(ChatColor.RED + "Your pet is already max level!");
			return;
		}

		pet.giveExp(Math.round(pet.getExpForNextLevel() - pet.getXp() + 1), Main.getInstance());

		Bukkit.getLogger().info(String.valueOf(Math.round(Pet.getExpForLevel(pet.getLevel() + 1) + pet.getXp())));
		Bukkit.getLogger().info(String.valueOf(pet.getExpForNextLevel()));
		Bukkit.getLogger().info(String.valueOf(pet.getXp()));

		candyStack.setAmount(candyStack.getAmount() - 1);

		p.getInventory().setItemInMainHand(candyStack);
	}

}
