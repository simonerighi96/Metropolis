package me.morpheus.metropolis.api.data.town.economy;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.value.mutable.Value;

public interface TaxData extends DataManipulator<TaxData, ImmutableTaxData> {

    Value<Double> tax();

}
