package com.orcacraft.smallpetsexpansion.pets;

import org.bukkit.entity.Player;

import it.smallcode.smallpets.core.pets.Pet;
import it.smallcode.smallpets.core.pets.PetType;
import it.smallcode.smallpets.v1_15.abilities.standard.HealthAbility;

// TODO: Auto-generated Javadoc
/**
 * The Class Enderman.
 */
public class Enderman extends Pet {

	/**
	 * Instantiates a new enderman.
	 *
	 * @param id the id
	 * @param owner the owner
	 * @param xp the xp
	 * @param useProtocolLib the use protocol lib
	 */
	public Enderman(String id, Player owner, Long xp, Boolean useProtocolLib) {

		super(id, owner, xp, useProtocolLib);

		super.setPetType(PetType.combat);


		super.abilities.add(new HealthAbility(20, 0));
	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.pets.Pet#updateTexture()
	 */
	@Override
	protected void updateTexture()
	{
		super.textureValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjMGIzNmQ1M2ZmZjY5YTQ5YzdkNmYzOTMyZjJiMGZlOTQ4ZTAzMjIyNmQ1ZTgwNDVlYzU4NDA4YTM2ZTk1MSJ9fX0=";
	}

}
