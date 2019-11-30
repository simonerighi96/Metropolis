package me.morpheus.metropolis.api.data.town;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.text.Text;

public interface TownData extends DataManipulator<TownData, ImmutableTownData> {

    OptionalValue<Text> description();

    OptionalValue<Text> motd();

}
