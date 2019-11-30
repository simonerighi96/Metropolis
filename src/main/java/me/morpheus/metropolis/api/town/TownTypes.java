package me.morpheus.metropolis.api.town;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class TownTypes {

    public static final TownType SETTLEMENT = DummyObjectProvider.createFor(TownType.class, "SETTLEMENT");

    private TownTypes() {}
}
