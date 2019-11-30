package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.flag.Flags;
import me.morpheus.metropolis.api.plot.PlotService;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.manipulator.mutable.entity.TameableData;
import org.spongepowered.api.data.property.item.SaturationProperty;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Hostile;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.Optional;

public class InteractHandler extends AbstractMPHandler {

    @Listener(beforeModifications = true)
    public void onInteractBlock(InteractBlockEvent event) {
        if (!event.getTargetBlock().getLocation().isPresent()) {
            return;
        }

        final Optional<ItemStackSnapshot> isOpt = event.getContext().get(EventContextKeys.USED_ITEM);
        if (isOpt.isPresent() && isOpt.get().getProperty(SaturationProperty.class).isPresent()) {
            return;
        }

        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
        final Optional<PlotData> pdOpt = ps.get(event.getTargetBlock().getLocation().get());

        if (!pdOpt.isPresent()) {
            return;
        }

        final Object root = event.getCause().root();

        if (!(root instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        if (!hasPermission((Player) root, pdOpt.get(), Flags.INTERACT_BLOCK)) {
            event.setCancelled(true);
            ((Player) root).sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "You don't have permission to do this"));
        }
    }

    @Listener(beforeModifications = true)
    public void onInteractItem(InteractItemEvent event) {
        if (!event.getInteractionPoint().isPresent()) {
            return;
        }

        if (event.getItemStack().getProperty(SaturationProperty.class).isPresent()) {
            return;
        }

        final Object root = event.getCause().root();

        if (!(root instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
        final Optional<PlotData> pdOpt = ps.get(((Player) root).getLocation().add(event.getInteractionPoint().get()));

        if (!pdOpt.isPresent()) {
            return;
        }

        if (!hasPermission((Player) root, pdOpt.get(), Flags.INTERACT_BLOCK)) {
            event.setCancelled(true);
            ((Player) root).sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "You don't have permission to do this"));
        }
    }

    @Listener(beforeModifications = true)
    public void onInteractEntity(InteractEntityEvent event) {
        final Entity entity = event.getTargetEntity();

        if (entity instanceof Hostile) {
            return;
        }

        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
        final Optional<PlotData> pdOpt = ps.get(entity.getLocation());

        if (!pdOpt.isPresent()) {
            return;
        }

        final Object root = event.getCause().root();

        if (!(root instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        final Optional<TameableData> optTameData = entity.get(TameableData.class);
        if (optTameData.isPresent() && optTameData.get().owner().get().isPresent() && optTameData.get().owner().get().get().equals(((Player) root).getUniqueId())) {
            return;
        }

        if (!hasPermission((Player) root, pdOpt.get(), Flags.INTERACT_ENTITY)) {
            event.setCancelled(true);
            ((Player) root).sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "You don't have permission to do this"));
        }
    }


}
