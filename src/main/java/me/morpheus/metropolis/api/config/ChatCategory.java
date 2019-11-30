package me.morpheus.metropolis.api.config;

import org.spongepowered.api.text.TextTemplate;

public interface ChatCategory {

    boolean isEnabled();

    TextTemplate getPrefix();

}
