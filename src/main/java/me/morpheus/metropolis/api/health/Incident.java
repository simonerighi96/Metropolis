package me.morpheus.metropolis.api.health;

import org.spongepowered.api.text.Text;

import java.util.Optional;

public interface Incident {

    Text getError();

    Optional<Throwable> getThrowable();

}
