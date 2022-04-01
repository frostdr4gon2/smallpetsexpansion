package com.orcacraft.smallpetsexpansion;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.orcacraft.smallpetsexpansion.commands.GiveRareCandy;
import com.orcacraft.smallpetsexpansion.events.OrcaBlockBreakListener;
import com.orcacraft.smallpetsexpansion.items.RareCandy;
import com.orcacraft.smallpetsexpansion.pets.Enderman;
import com.orcacraft.smallpetsexpansion.pets.IronGolem;
import com.orcacraft.smallpetsexpansion.pets.Orca;
import com.orcacraft.smallpetsexpansion.pets.Pig;
import com.orcacraft.smallpetsexpansion.pets.abilities.GreenHandAbility;
import com.orcacraft.smallpetsexpansion.pets.abilities.SlowAbility;
import com.orcacraft.smallpetsexpansion.pets.abilities.WaterBreathingAbility;

import it.smallcode.smallpets.core.SmallPetsCommons;

public class Main extends JavaPlugin {
	private static Main instance;
	static RareCandy candy;

	@Override
	public void onEnable() {
		instance = this;
		candy = new RareCandy();

		credits();

		Bukkit.getPluginManager().registerEvents(new OrcaBlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(candy, this);

		registerPets();
		registerCommands();

	}

	private void registerPets() {

		registerAbilities();

		SmallPetsCommons.getSmallPetsCommons().getPetMapManager().registerPet("orca", Orca.class);
		SmallPetsCommons.getSmallPetsCommons().getPetMapManager().registerPet("enderman", Enderman.class);
		SmallPetsCommons.getSmallPetsCommons().getPetMapManager().registerPet("iron_golem", IronGolem.class);
		SmallPetsCommons.getSmallPetsCommons().getPetMapManager().registerPet("pig", Pig.class);

	}

	private void registerAbilities() {

		SmallPetsCommons.getSmallPetsCommons().getAbilityManager().registerAbility("slowness_ability",
				SlowAbility.class);
		SmallPetsCommons.getSmallPetsCommons().getAbilityManager().registerAbility("green_hand_ability",
				GreenHandAbility.class);
		SmallPetsCommons.getSmallPetsCommons().getAbilityManager().registerAbility("water_breathing_ability",
				WaterBreathingAbility.class);
	}

	private void registerCommands() {
		getCommand("giverarecandy").setExecutor(new GiveRareCandy());
	}

	@Override
	public void onDisable() {

	}

	public static Main getInstance() {
		return instance;
	}

	public static RareCandy getCandy() {
		return candy;
	}

	private void credits() {
		Bukkit.getLogger().info("Coded by Frostdr4gon2 - Owner of Play.OrcaCraft.com | OrcaCraft.com");
	}
}
