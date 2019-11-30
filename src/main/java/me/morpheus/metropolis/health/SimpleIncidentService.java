package me.morpheus.metropolis.health;

import me.morpheus.metropolis.api.health.IncidentService;
import me.morpheus.metropolis.api.health.Incident;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class SimpleIncidentService implements IncidentService {

    private final List<Incident> incidents = new ArrayList<>();

    @Override
    public void create(Incident i) {
        this.incidents.add(i);
    }

    @Override
    public Collection<Incident> getAll() {
        return Collections.unmodifiableList(this.incidents);
    }
}
