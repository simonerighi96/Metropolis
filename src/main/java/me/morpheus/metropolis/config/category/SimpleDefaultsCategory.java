package me.morpheus.metropolis.config.category;

import me.morpheus.metropolis.api.config.DefaultsCategory;
import me.morpheus.metropolis.api.town.pvp.PvPOption;
import me.morpheus.metropolis.api.town.pvp.PvPOptions;
import me.morpheus.metropolis.api.town.visibility.Visibilities;
import me.morpheus.metropolis.api.town.visibility.Visibility;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class SimpleDefaultsCategory implements DefaultsCategory {

    @Setting(value = "pvp")
    private PvPOption pvp = PvPOptions.OFF;

    @Setting(value = "visibility")
    private Visibility visibility = Visibilities.PRIVATE;

    @Override
    public PvPOption pvp() {
        return this.pvp;
    }

    @Override
    public Visibility visibility() {
        return this.visibility;
    }
}
