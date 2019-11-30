package me.morpheus.metropolis.data.plot;

import me.morpheus.metropolis.api.data.plot.ImmutablePlotData;
import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;

public class MPPlotDataBuilder extends AbstractDataBuilder<PlotData> implements DataManipulatorBuilder<PlotData, ImmutablePlotData> {

    public MPPlotDataBuilder() {
        super(PlotData.class, DataVersions.PlotData.CURRENT_VERSION);
    }

    @Override
    public PlotData create() {
        return new MPPlotData();
    }

    @Override
    public Optional<PlotData> createFrom(DataHolder dataHolder) {
        return create().fill(dataHolder);
    }

    @Override
    protected Optional<PlotData> buildContent(DataView container) throws InvalidDataException {
        return create().from(container.getContainer());
    }
}
