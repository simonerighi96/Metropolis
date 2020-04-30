package me.morpheus.metropolis.town.listeners;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2ShortMap;
import me.morpheus.metropolis.api.plot.PlotType;
import me.morpheus.metropolis.api.event.town.TownTransactionEvent;
import me.morpheus.metropolis.town.MPTown;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;

public class InternalTownTransactionHandler {

    @Listener(beforeModifications = true)
    public void onUpkeep(TownTransactionEvent.Upkeep event) {
        final MPTown t = (MPTown) event.getTargetTown();
        event.addSupplier("citizens", t::getCitizenCount);
        for (PlotType type : Sponge.getRegistry().getAllOf(PlotType.class)) {
            event.addSupplier(type.getId(), () -> t.getPlots().getShort(type));
        }
    }
}
