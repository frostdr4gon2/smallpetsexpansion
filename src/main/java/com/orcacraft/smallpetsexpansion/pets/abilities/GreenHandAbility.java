package com.orcacraft.smallpetsexpansion.pets.abilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.orcacraft.smallpetsexpansion.pets.events.PetBlockBreakEvent;

import it.smallcode.smallpets.core.abilities.Ability;
import it.smallcode.smallpets.core.abilities.AbilityType;
import it.smallcode.smallpets.core.abilities.eventsystem.AbilityEventHandler;
import it.smallcode.smallpets.core.abilities.templates.StatBoostAbility;

/**
 * The Class GreenHandAbility.
 */
public class GreenHandAbility extends StatBoostAbility {

	/**
	 * Instantiates a new green hand ability.
	 */
	public GreenHandAbility() {
		this(0);
	}

	/**
	 * Instantiates a new green hand ability.
	 *
	 * @param maxExtraStat the max extra stat
	 */
	public GreenHandAbility(double maxExtraStat) {
		this(maxExtraStat, 0);
	}

	/**
	 * Instantiates a new green hand ability.
	 *
	 * @param maxExtraStat the max extra stat
	 * @param minExtraStat the min extra stat
	 */
	public GreenHandAbility(double maxExtraStat, double minExtraStat) {

		super(maxExtraStat, minExtraStat, NumberDisplayType.INTEGER);
		super.setAbilityType(AbilityType.ABILITY);

	}

	/**
	 * On crop break.
	 *
	 * @param e the PetBlockBreakEvent
	 */
	@AbilityEventHandler
	public void onCropBreak(PetBlockBreakEvent e) {

		if (e.getUser().getSelected().hasAbility(getID())) {
			final Material m = e.getBlock().getType();

			final Material[] crops = { Material.WHEAT, Material.BEETROOTS, Material.CARROTS, Material.POTATOES,
					Material.MELON, Material.PUMPKIN, Material.COCOA, Material.NETHER_WART };

			for (int i = 0; i < crops.length; i++) {
				if (crops[i] == m) {
					final Collection<ItemStack> drops = e.getBlock().getDrops();

					final StatBoostAbility ability = (StatBoostAbility) e.getUser().getSelected().getAbility(getID());

					final List<ItemStack> items = new ArrayList<>();

					final ArrayList<ItemStack> item = new ArrayList<>(drops);

					Bukkit.getLogger().info(item.toString());

					final int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
					final int boost = (int) ability.getExtraStat(e.getUser().getSelected().getLevel());

					if (randomNum <= boost) {

						for (int ii = 0; ii < item.size(); ii++) {
							final int amount = item.get(ii).getAmount() * 1;
							final Material mat = item.get(ii).getType();

							items.add(new ItemStack(mat, amount));

							Bukkit.getLogger().info(items.toString());
							Bukkit.getLogger().info(String.valueOf(amount));
						}
					}

					e.setDrops(items);

					break;
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.abilities.templates.StatBoostAbility#addBoost(org.bukkit.entity.Player, it.smallcode.smallpets.core.abilities.Ability)
	 */
	@Override
	public void addBoost(Player arg0, Ability arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.abilities.templates.StatBoostAbility#removeBoost(org.bukkit.entity.Player, it.smallcode.smallpets.core.abilities.Ability)
	 */
	@Override
	public void removeBoost(Player arg0, Ability arg1) {
		// TODO Auto-generated method stub

	}

}
