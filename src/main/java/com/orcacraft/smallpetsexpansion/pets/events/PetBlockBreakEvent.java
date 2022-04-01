package com.orcacraft.smallpetsexpansion.pets.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

import it.smallcode.smallpets.core.abilities.eventsystem.AbilityEvent;
import it.smallcode.smallpets.core.manager.types.User;

/**
 * The Class PetBlockBreakEvent.
 */
public class PetBlockBreakEvent extends AbilityEvent implements Cancellable {

	/** The cancelled. */
	private boolean cancelled = false;

	/** The block. */
	private final Block block;

	/** The drops. */
	private List<ItemStack> drops = new ArrayList<>();

	/**
	 * Instantiates a new pet block break event.
	 *
	 * @param user the user
	 * @param block the block
	 */
	public PetBlockBreakEvent(User user, Block block) {
		super(user);

		this.block = block;
	}

	/**
	 * Gets the block.
	 *
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Gets the drops.
	 *
	 * @return the drops
	 */
	public List<ItemStack> getDrops() {
		return drops;
	}

	/**
	 * Sets the drops.
	 *
	 * @param drops the new drops
	 */
	public void setDrops(List<ItemStack> drops) {

		this.drops = drops;
	}

	/* (non-Javadoc)
	 * @see org.bukkit.event.Cancellable#isCancelled()
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	/* (non-Javadoc)
	 * @see org.bukkit.event.Cancellable#setCancelled(boolean)
	 */
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
