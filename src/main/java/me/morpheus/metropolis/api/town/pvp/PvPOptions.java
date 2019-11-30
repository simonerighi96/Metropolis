package me.morpheus.metropolis.api.town.pvp;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class PvPOptions {

    public static final PvPOption ON = DummyObjectProvider.createFor(PvPOption.class, "ON");
    public static final PvPOption OFF = DummyObjectProvider.createFor(PvPOption.class, "OFF");
    public static final PvPOption FRIENDLY_FIRE = DummyObjectProvider.createFor(PvPOption.class, "FRIENDLY_FIRE");
    public static final PvPOption GRACE_PERIOD = DummyObjectProvider.createFor(PvPOption.class, "GRACE_PERIOD");

    private PvPOptions() {}
}
