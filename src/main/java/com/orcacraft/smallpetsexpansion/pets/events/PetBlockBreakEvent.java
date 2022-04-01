package com.orcacraft.smallpetsexpansion.pets.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

import it.smallcode.smallpets.core.abilities.eventsystem.AbilityEvent;
import it.smallcode.smallpets.core.manager.types.User;

public class PetBlockBreakEvent extends AbilityEvent implements Cancellable {

	private boolean cancelled = false;
	private final Block block;
	private List<ItemStack> drops = new ArrayList<>();

	public PetBlockBreakEvent(User user, Block block) {
		super(user);

		this.block = block;
	}

	public Block getBlock() {
		return block;
	}

	public List<ItemStack> getDrops() {
		return drops;
	}

	public void setDrops(List<ItemStack> drops) {

		this.drops = drops;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
