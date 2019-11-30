package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.flag.Flags;
import me.morpheus.metropolis.api.plot.PlotService;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.api.town.TownService;

import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Hostile;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

public class DamageEntityHandler extends AbstractMPHandler {

    @Listener(beforeModifications = true)
    public void onDamage(DamageEntityEvent event) {
        final Entity target = event.getTargetEntity();

        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
        final Optional<PlotData> pdOpt = ps.get(target.getLocation());

        if (!pdOpt.isPresent()) {
            return;
        }

        if (target instanceof Hostile) {
            return;
        }

        final Object source = getSource(event);

        if (!(source instanceof Player)) {
            event.setCancelled(true);
            return;
        }

        if (target instanceof Player) {
            final TownService ts = Sponge.getServiceManager().provideUnchecked(TownService.class);
            final Town t = ts.get(pdOpt.get().town().get().intValue()).get();
            if (!t.getPvP().canDamage((Player) source, (Player) target)) {
                event.setCancelled(true);
                ((Player) source).sendMessage(TextUtil.watermark(TextColors.RED, "You don't have permission to do this"));
            }
            return;
        }

        if (!hasPermission((Player) source, pdOpt.get(), Flags.DAMAGE)) {
            event.setCancelled(true);
            ((Player) source).sendMessage(TextUtil.watermark(TextColors.RED, "You don't have permission to do this"));
        }
    }

    private Object getSource(DamageEntityEvent event) {
        final Object root = event.getCause().root();

        if (root instanceof IndirectEntityDamageSource) {
            return ((IndirectEntityDamageSource) root).getIndirectSource();
        }

        if (root instanceof EntityDamageSource) {
            return ((EntityDamageSource) root).getSource();
        }

        return root;
    }
}
