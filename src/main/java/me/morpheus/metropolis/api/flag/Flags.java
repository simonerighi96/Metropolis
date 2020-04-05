package me.morpheus.metropolis.api.flag;

import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public final class Flags {

    public static final Flag BLOCK_PLACE = DummyObjectProvider.createFor(Flag.class, "BLOCK_PLACE");
    public static final Flag BLOCK_BREAK = DummyObjectProvider.createFor(Flag.class, "BLOCK_BREAK");
    public static final Flag BLOCK_CHANGE = DummyObjectProvider.createFor(Flag.class, "BLOCK_CHANGE");
    public static final Flag INTERACT_BLOCK = DummyObjectProvider.createFor(Flag.class, "INTERACT_BLOCK");
    public static final Flag INTERACT_ENTITY = DummyObjectProvider.createFor(Flag.class, "INTERACT_ENTITY");
    public static final Flag INTERACT_INVENTORY = DummyObjectProvider.createFor(Flag.class, "INTERACT_INVENTORY");
    public static final Flag DAMAGE = DummyObjectProvider.createFor(Flag.class, "DAMAGE");
    public static final Flag EXPLOSION = DummyObjectProvider.createFor(Flag.class, "EXPLOSION");

    private Flags(){}

}
