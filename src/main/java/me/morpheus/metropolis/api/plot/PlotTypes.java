package me.morpheus.metropolis.api.plot;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class PlotTypes {

    public static final PlotType PLOT = DummyObjectProvider.createFor(PlotType.class, "PLOT");
    public static final PlotType HOMEBLOCK = DummyObjectProvider.createFor(PlotType.class, "HOMEBLOCK");
    public static final PlotType OUTPOST = DummyObjectProvider.createFor(PlotType.class, "OUTPOST");

    private PlotTypes() {}
}
