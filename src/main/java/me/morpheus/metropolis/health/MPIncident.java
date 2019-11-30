package me.morpheus.metropolis.health;

import me.morpheus.metropolis.api.health.Incident;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.Optional;

public class MPIncident implements Incident {

    private final Text error;
    @Nullable private final Throwable throwable;

    public MPIncident(Text error, @Nullable Throwable throwable) {
        this.error = error;
        this.throwable = throwable;
    }

    public MPIncident(Text error) {
        this(error, null);
    }

    @Override
    public Text getError() {
        return this.error;
    }

    @Override
    public Optional<Throwable> getThrowable() {
        return Optional.ofNullable(this.throwable);
    }
}
