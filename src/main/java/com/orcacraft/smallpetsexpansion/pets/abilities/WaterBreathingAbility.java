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

public class WaterBreathingAbility extends StatBoostAbility {


	public WaterBreathingAbility(){
		this(0);
	}

	public WaterBreathingAbility(double maxExtraStat) {
		this(maxExtraStat, 0);
	}

	public WaterBreathingAbility(double maxExtraStat, double minExtraStat) {

		super(maxExtraStat, minExtraStat, NumberDisplayType.INTEGER);
		super.setAbilityType(AbilityType.ABILITY);

	}

	@AbilityEventHandler
	public void onLevelUp(PetLevelUpEvent e){
		if(e.getUser().getSelected() == null)
			return;
		if(e.getUser().getSelected().hasAbility(getID())) {

			removeBoost(e.getPet().getOwner(), e.getPet().getAbility(getID()));
			addBoost(e.getPet().getOwner(), e.getPet().getAbility(getID()));

		}

	}

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

	@AbilityEventHandler
	public void onDeselect(PetDeselectEvent e){

		if(e.getPet().hasAbility(getID())) {

			removeBoost(e.getOwner(), e.getPet().getAbility(getID()));
		}

	}

	@AbilityEventHandler
	public void onQuit(QuitEvent e){

		if(e.getUser().getSelected().hasAbility(getID())) {

			removeBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));

		}

	}

	@AbilityEventHandler
	public void onJoin(JoinEvent e){

		if(e.getUser().getSelected().hasAbility(getID())) {

			addBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));

		}

	}

	@AbilityEventHandler
	public void onShutdown(ServerShutdownEvent e){

		if(e.getUser() != null && e.getUser().getSelected() != null)
			if(e.getUser().getSelected().hasAbility(getID())) {

				removeBoost(e.getUser().getSelected().getOwner(), e.getUser().getSelected().getAbility(getID()));
			}

	}

	@Override
	public void addBoost(Player p, Ability ability) {

		final StatBoostAbility statBoostAbility = (StatBoostAbility) ability;
		final User user = SmallPetsCommons.getSmallPetsCommons().getUserManager().getUser(p.getUniqueId().toString());

		if(user == null)
			return;

		final int potionEffectLevel = (int) statBoostAbility.getExtraStat(user.getSelected().getLevel()) -1;
		final PotionEffect potionEffect = new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000000, potionEffectLevel, false, false);

		p.addPotionEffect(potionEffect);

		debug(getID() + " give effect " + p.hasPotionEffect(PotionEffectType.WATER_BREATHING));

	}

	@Override
	public void removeBoost(Player p, Ability ability) {

		p.removePotionEffect(PotionEffectType.WATER_BREATHING);
		debug(getID() + " remove effect " + p.hasPotionEffect(PotionEffectType.WATER_BREATHING));

	}
}
