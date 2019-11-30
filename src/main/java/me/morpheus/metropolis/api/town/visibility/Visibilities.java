package me.morpheus.metropolis.api.town.visibility;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class Visibilities {

    public static final Visibility PUBLIC = DummyObjectProvider.createFor(Visibility.class, "PUBLIC");
    public static final Visibility PRIVATE = DummyObjectProvider.createFor(Visibility.class, "PRIVATE");

    private Visibilities() {}
}
