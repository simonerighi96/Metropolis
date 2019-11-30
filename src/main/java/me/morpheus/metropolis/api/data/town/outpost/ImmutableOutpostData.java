package me.morpheus.metropolis.api.data.town.outpost;

import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.immutable.ImmutableMapValue;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public interface ImmutableOutpostData extends ImmutableDataManipulator<ImmutableOutpostData, OutpostData> {

    ImmutableMapValue<String, Location<World>> outposts();

}
