package me.morpheus.metropolis.api.event.item.inventory;

import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

public interface InteractInventoryTownEvent extends Event, Cancellable {

    interface Open extends InteractInventoryTownEvent {}
}
