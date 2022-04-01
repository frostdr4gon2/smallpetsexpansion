package com.orcacraft.smallpetsexpansion.pets.abilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import it.smallcode.smallpets.core.SmallPetsCommons;
import it.smallcode.smallpets.core.abilities.Ability;
import it.smallcode.smallpets.core.abilities.AbilityType;
import it.smallcode.smallpets.core.abilities.eventsystem.AbilityEventHandler;
import it.smallcode.smallpets.core.abilities.eventsystem.events.JoinEvent;
import it.smallcode.smallpets.core.abilities.eventsystem.events.PetDeselectEvent;
import it.smallcode.smallpets.core.abilities.eventsystem.events.PetLevelUpEvent;
import it.smallcode.smallpets.core.abilities.eventsystem.events.PetSelectEvent;
import it.smallcode.smallpets.core.abilities.eventsystem.events.QuitEvent;
import it.smallcode.smallpets.core.abilities.eventsystem.events.ServerShutdownEvent;
import it.smallcode.smallpets.core.abilities.templates.StatBoostAbility;
import it.smallcode.smallpets.core.manager.types.User;

/**
 * The Class SlowAbility.
 */
public class SlowAbility extends StatBoostAbility {


	/**
	 * Instantiates a new slow ability.
	 */
	public SlowAbility(){
		this(0);
	}

	/**
	 * Instantiates a new slow ability.
	 *
	 * @param maxExtraStat the max extra stat
	 */
	public SlowAbility(double maxExtraStat) {
		this(maxExtraStat, 0);
	}

	/**
	 * Instantiates a new slow ability.
	 *
	 * @param maxExtraStat the max extra stat
	 * @param minExtraStat the min extra stat
	 */
	public SlowAbility(double maxExtraStat, double minExtraStat) {

		super(maxExtraStat, minExtraStat, NumberDisplayType.INTEGER);
		super.setAbilityType(AbilityType.ABILITY);

	}

	/**
	 * On level up.
	 *
	 * @param e the PetLevelUpEvent
	 */
	@AbilityEventHandler
	public void onLevelUp(PetLevelUpEvent e){
		if(e.getUser().getSelected() == null)
			return;
		if(e.getUser().getSelected().hasAbility(getID())) {

			removeBoost(e.getPet().getOwner(), e.getPet().getAbility(getID()));
			addBoost(e.getPet().getOwner(), e.getPet().getAbility(getID()));

		}

	}

	/**
	 * On select.
	 *
	 * @param e the PetSelectEvent
	 */
	@AbilityEventHandler
	public void onSelect(PetSelectEvent e){

		if(e.getPet().hasAbility(getID())) {

			addBoost(e.getOwner(), e.getPet().getAbility(getID()));
		}

		Bukkit.getLogger().warning(getID() + " id");

		for (int i = 0; i < e.getUser().getSelected().getAbilities().size(); i++) {
			Bukkit.getLogger().warning(e.getUser().getSelected().getAbilities().get(i).getID());
		}

	}

	/**
	 * On deselect.
	 *
	 * @param e the PetDeselectEvent
	 */
	@AbilityEventHandler
	public void onDeselect(PetDeselectEvent e){

		if(e.getPet().hasAbility(getID())) {

			removeBoost(e.getOwner(), e.getPet().getAbility(getID()));
		}

	}

	/**
	 * On quit.
	 *
	 * @param e the QuitEvent
	 */
	@AbilityEventHandler
	public void onQuit(QuitEvent e){

		if(e.getUser().getSelected().hasAbility(getID())) {

			removeBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));

		}

	}

	/**
	 * On join.
	 *
	 * @param e the JoinEvent
	 */
	@AbilityEventHandler
	public void onJoin(JoinEvent e){

		if(e.getUser().getSelected().hasAbility(getID())) {

			addBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));

		}

	}

	/**
	 * On shutdown.
	 *
	 * @param e the ServerShutdownEvent
	 */
	@AbilityEventHandler
	public void onShutdown(ServerShutdownEvent e){

		if(e.getUser() != null && e.getUser().getSelected() != null)
			if(e.getUser().getSelected().hasAbility(getID())) {

				removeBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));
			}

	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.abilities.templates.StatBoostAbility#addBoost(org.bukkit.entity.Player, it.smallcode.smallpets.core.abilities.Ability)
	 */
	@Override
	public void addBoost(Player p, Ability ability) {

		final StatBoostAbility statBoostAbility = (StatBoostAbility) ability;
		final User user = SmallPetsCommons.getSmallPetsCommons().getUserManager().getUser(p.getUniqueId().toString());

		if(user == null)
			return;

		final int potionEffectLevel = (int) statBoostAbility.getExtraStat(user.getSelected().getLevel()) -1;
		final PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 1000000000, potionEffectLevel, false, false);

		p.addPotionEffect(potionEffect);

		debug(getID() + " give effect " + p.hasPotionEffect(PotionEffectType.SLOW));

	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.abilities.templates.StatBoostAbility#removeBoost(org.bukkit.entity.Player, it.smallcode.smallpets.core.abilities.Ability)
	 */
	@Override
	public void removeBoost(Player p, Ability ability) {

		p.removePotionEffect(PotionEffectType.SLOW);
		debug(getID() + " remove effect " + p.hasPotionEffect(PotionEffectType.SLOW));

	}
}
