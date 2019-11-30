package me.morpheus.metropolis.api.data.town.economy;

import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

public interface ImmutableTaxData extends ImmutableDataManipulator<ImmutableTaxData, TaxData> {

    ImmutableValue<Double> tax();

}
