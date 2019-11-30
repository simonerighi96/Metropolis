package me.morpheus.metropolis.data.town.economy;

import me.morpheus.metropolis.api.data.town.TownKeys;
import me.morpheus.metropolis.api.data.town.economy.ImmutableTaxData;
import me.morpheus.metropolis.api.data.town.economy.TaxData;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;

public class MPTaxData extends AbstractData<TaxData, ImmutableTaxData> implements TaxData {

    private double tax;

    MPTaxData() {
        this(0);
    }

    MPTaxData(double tax) {
        this.tax = tax;
        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        registerKeyValue(TownKeys.TAX, this::tax);

        registerFieldGetter(TownKeys.TAX, this::getTax);

        // Only on mutable implementation
        registerFieldSetter(TownKeys.TAX, this::setTax);
    }

    @Override
    public Optional<TaxData> fill(DataHolder dataHolder, MergeFunction overlap) {
        TaxData merged = overlap.merge(this, dataHolder.get(TaxData.class).orElse(null));
        this.tax = merged.tax().get().doubleValue();

        return Optional.of(this);
    }

    @Override
    public Optional<TaxData> from(DataContainer container) {
        Optional<Double> taxOpt = container.getDouble(TownKeys.TAX.getQuery());
        if (!taxOpt.isPresent()) {
            return Optional.empty();
        }

        this.tax = taxOpt.get();
        return Optional.of(this);
    }

    @Override
    public TaxData copy() {
        return new MPTaxData(this.tax);
    }

    @Override
    public ImmutableTaxData asImmutable() {
        return new ImmutableMPTaxData(this.tax);
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
    public Value<Double> tax() {
        return Sponge.getRegistry().getValueFactory().createValue(TownKeys.TAX, this.tax);
    }

    private double getTax() {
        return this.tax;
    }

    private void setTax(double tax) {
        this.tax = tax;
    }
}
