package me.morpheus.metropolis.api.data.town;

import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.immutable.ImmutableOptionalValue;
import org.spongepowered.api.text.Text;

public interface ImmutableTownData extends ImmutableDataManipulator<ImmutableTownData, TownData> {

    ImmutableOptionalValue<Text> description();

    ImmutableOptionalValue<Text> motd();

}
