package com.orcacraft.smallpetsexpansion.pets;

import org.bukkit.entity.Player;

import com.orcacraft.smallpetsexpansion.pets.abilities.GreenHandAbility;

import it.smallcode.smallpets.core.pets.Pet;
import it.smallcode.smallpets.core.pets.PetType;

/**
 * The Class Pig.
 */
public class Pig extends Pet {

	/**
	 * Instantiates a new pig.
	 *
	 * @param id the id
	 * @param owner the owner
	 * @param xp the xp
	 * @param useProtocolLib the use protocol lib
	 */
	public Pig(String id, Player owner, Long xp, Boolean useProtocolLib) {

		super(id, owner, xp, useProtocolLib);

		super.setPetType(PetType.farming);


		super.abilities.add(new GreenHandAbility(100, 0));
	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.pets.Pet#updateTexture()
	 */
	@Override
	protected void updateTexture()
	{
		super.textureValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=";
	}

}
