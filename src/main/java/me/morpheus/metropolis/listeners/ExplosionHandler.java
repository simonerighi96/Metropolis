package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.flag.Flags;
import me.morpheus.metropolis.api.plot.PlotService;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.entity.explosive.DetonateExplosiveEvent;
import org.spongepowered.api.event.world.ExplosionEvent;

import java.util.Optional;

public class ExplosionHandler extends AbstractMPHandler {

    @Listener(beforeModifications = true)
    public void onExplosionPre(ExplosionEvent.Pre event) {
        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);

        final Optional<User> ownerOpt = event.getContext().get(EventContextKeys.OWNER);
        if (!ownerOpt.isPresent()) {
            event.setCancelled(true);
            return;
        }

        if (ps.get(event.getExplosion()).anyMatch(pd -> pd != null && !hasPermission(ownerOpt.get(), pd, Flags.EXPLOSION))) {
            event.setCancelled(true);
        }
    }

    @Listener(beforeModifications = true)
    public void onDetonateExplosive(DetonateExplosiveEvent event) {
        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);

        final Optional<User> ownerOpt = event.getContext().get(EventContextKeys.OWNER);
        if (!ownerOpt.isPresent()) {
            event.setCancelled(true);
            return;
        }

        if (ps.get(event.getOriginalExplosion()).anyMatch(pd -> pd != null && !hasPermission(ownerOpt.get(), pd, Flags.EXPLOSION))) {
            event.setCancelled(true);
        }
    }

}
