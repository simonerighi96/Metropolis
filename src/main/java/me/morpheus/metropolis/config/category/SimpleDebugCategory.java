package me.morpheus.metropolis.config.category;

import me.morpheus.metropolis.api.config.DebugCategory;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class SimpleDebugCategory implements DebugCategory {

    @Setting
    private boolean enabled = false;

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
