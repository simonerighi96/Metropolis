package me.morpheus.metropolis.data.town.economy;

import me.morpheus.metropolis.api.data.town.TownKeys;
import me.morpheus.metropolis.api.data.town.economy.ImmutableTaxData;
import me.morpheus.metropolis.api.data.town.economy.TaxData;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

public class ImmutableMPTaxData extends AbstractImmutableData<ImmutableTaxData, TaxData> implements ImmutableTaxData {

    private final double tax;

    ImmutableMPTaxData(double tax) {
        this.tax = tax;
        registerGetters();
    }
    @Override
    protected void registerGetters() {
        registerKeyValue(TownKeys.TAX, this::tax);

        registerFieldGetter(TownKeys.TAX, this::getTax);
    }

    @Override
    public TaxData asMutable() {
        return new MPTaxData(this.tax);
    }

    @Override
    public int getContentVersion() {
        return DataVersions.TaxData.CURRENT_VERSION;
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        container.set(TownKeys.TAX.getQuery(), this.tax);

        return container;
    }

    @Override
    public ImmutableValue<Double> tax() {
        return Sponge.getRegistry().getValueFactory().createValue(TownKeys.TAX, this.tax).asImmutable();
    }

    private double getTax() {
        return this.tax;
    }
}
