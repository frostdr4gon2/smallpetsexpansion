package com.orcacraft.smallpetsexpansion.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.orcacraft.smallpetsexpansion.pets.events.PetBlockBreakEvent;

import it.smallcode.smallpets.core.SmallPetsCommons;
import it.smallcode.smallpets.core.abilities.eventsystem.AbilityEventBus;
import it.smallcode.smallpets.core.manager.types.User;

public class OrcaBlockBreakListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDamage(BlockBreakEvent e) {

		final Player p = e.getPlayer();

		final User user = SmallPetsCommons.getSmallPetsCommons().getUserManager().getUser(p.getUniqueId().toString());

		if (user != null) {

			if (user.getSelected() != null) {

				if (!e.isCancelled()) {

					final PetBlockBreakEvent blockBreakEvent = new PetBlockBreakEvent(user, e.getBlock());
					blockBreakEvent.setCancelled(e.isCancelled());

					AbilityEventBus.post(blockBreakEvent);

					e.setCancelled(blockBreakEvent.isCancelled());

					if (!blockBreakEvent.getDrops().isEmpty()) {
						final World world = e.getBlock().getWorld();
						final Location loc = e.getBlock().getLocation();

						for (int i = 0; i < blockBreakEvent.getDrops().size(); i++) {

							world.dropItemNaturally(loc, blockBreakEvent.getDrops().get(i));

						}
					}

				}

			}

		}

	}

}
