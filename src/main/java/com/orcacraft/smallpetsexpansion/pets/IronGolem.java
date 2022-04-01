package com.orcacraft.smallpetsexpansion.pets;

import org.bukkit.entity.Player;

import com.orcacraft.smallpetsexpansion.pets.abilities.SlowAbility;

import it.smallcode.smallpets.core.pets.Pet;
import it.smallcode.smallpets.core.pets.PetType;
import it.smallcode.smallpets.v1_15.abilities.standard.HealthAbility;

/**
 * The Class IronGolem.
 */
public class IronGolem extends Pet {

	/**
	 * Instantiates a new iron golem.
	 *
	 * @param id the id
	 * @param owner the owner
	 * @param xp the xp
	 * @param useProtocolLib the use protocol lib
	 */
	public IronGolem(String id, Player owner, Long xp, Boolean useProtocolLib) {

		super(id, owner, xp, useProtocolLib);

		super.setPetType(PetType.combat);

		super.abilities.add(new SlowAbility(2, 0));
		super.abilities.add(new HealthAbility(60, 0));

	}

	/* (non-Javadoc)
	 * @see it.smallcode.smallpets.core.pets.Pet#updateTexture()
	 */
	@Override
	protected void updateTexture() {
		super.textureValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEzZjM0MjI3MjgzNzk2YmMwMTcyNDRjYjQ2NTU3ZDY0YmQ1NjJmYTlkYWIwZTEyYWY1ZDIzYWQ2OTljZjY5NyJ9fX0=";
	}

}
