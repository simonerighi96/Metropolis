package me.morpheus.metropolis.event.item.inventory;

import me.morpheus.metropolis.api.event.item.inventory.InteractInventoryTownEvent;
import org.spongepowered.api.event.cause.Cause;

public class MPInteractInventoryTownEventOpen implements InteractInventoryTownEvent.Open {

    private final Cause cause;
    private boolean cancelled = false;

    public MPInteractInventoryTownEventOpen(Cause cause) {
        this.cause = cause;
    }

    @Override
    public Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
