package com.orcacraft.smallpetsexpansion.pets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.orcacraft.smallpetsexpansion.pets.abilities.WaterBreathingAbility;

import it.smallcode.smallpets.core.pets.Pet;
import it.smallcode.smallpets.core.pets.PetType;
import it.smallcode.smallpets.v1_15.abilities.standard.SpeedBoostInBiomeAbility;

/**
 * The Class Orca.
 */
public class Orca extends Pet {

	/** The biomes. */
	final List<Biome> biomes = new ArrayList<>();

	/**
	 * Instantiates a new orca.
	 *
	 * @param id the id
	 * @param owner the owner
	 * @param xp the xp
	 * @param useProtocolLib the use protocol lib
	 */
	public Orca(String id, Player owner, Long xp, Boolean useProtocolLib) {

		super(id, owner, xp, useProtocolLib);

		super.setPetType(PetType.fishing);

		addBiomes();

		super.abilities.add(new WaterBreathingAbility());
		super.abilities.add(new SpeedBoostInBiomeAbility(biomes, 0.01D, 0.25D));
	}

	/**
	 * Adds the biomes.
	 */
	private void addBiomes()
	{
		biomes.add(Biome.COLD_OCEAN);
		biomes.add(Biome.DEEP_COLD_OCEAN);
		biomes.add(Biome.DEEP_FROZEN_OCEAN);
		biomes.add(Biome.DEEP_LUKEWARM_OCEAN);
		biomes.add(Biome.DEEP_OCEAN);
		biomes.add(Biome.FROZEN_OCEAN);
		biomes.add(Biome.FROZEN_RIVER);
		biomes.add(Biome.LUKEWARM_OCEAN);
		biomes.add(Biome.OCEAN);
		biomes.add(Biome.RIVER);
		biomes.add(Biome.WARM_OCEAN);
	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.pets.Pet#updateTexture()
	 */
	@Override
	protected void updateTexture()
	{
		super.textureValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA1ZmI0ZTVkN2Q3NWM1ZjQ0NjZkZjUwMGRiN2ViZTBlOWUxYmRkYjM2MDZlYjI5YmMyZWQ1N2RmNWViZWY4MyJ9fX0=";
	}

}
