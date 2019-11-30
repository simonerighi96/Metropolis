package me.morpheus.metropolis.api.data.town.outpost;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public interface OutpostData extends DataManipulator<OutpostData, ImmutableOutpostData> {

    MapValue<String, Location<World>> outposts();

}
