package me.morpheus.metropolis.api.config;

import me.morpheus.metropolis.api.town.pvp.PvPOption;
import me.morpheus.metropolis.api.town.visibility.Visibility;

public interface DefaultsCategory {

    PvPOption pvp();

    Visibility visibility();

}
