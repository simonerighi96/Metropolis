package me.morpheus.metropolis.api.town.invitation;

import me.morpheus.metropolis.api.town.Town;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

public interface InvitationService {

    Invitation create(@Nullable UUID source, UUID target, Town t);

    void remove(Predicate<Invitation> predicate);

    void remove(UUID target, Predicate<Invitation> predicate);

    Collection<Invitation> getAll();

    Collection<Invitation> getAll(UUID target);

}
