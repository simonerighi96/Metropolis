package me.morpheus.metropolis.api.rank;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class Ranks {

    public static final Rank MAYOR = DummyObjectProvider.createFor(Rank.class, "MAYOR");
    public static final Rank CITIZEN = DummyObjectProvider.createFor(Rank.class, "CITIZEN");

    private Ranks() {}
}
