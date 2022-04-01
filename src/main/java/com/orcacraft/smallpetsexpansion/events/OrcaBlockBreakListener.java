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

/**
 * The listener interface for receiving orcaBlockBreak events.
 * The class that is interested in processing a orcaBlockBreak
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOrcaBlockBreakListener<code> method. When
 * the orcaBlockBreak event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OrcaBlockBreakEvent
 */
public class OrcaBlockBreakListener implements Listener {

	/**
	 * On Block Break.
	 *
	 * @param e the BlockBreakEvent
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e) {

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
