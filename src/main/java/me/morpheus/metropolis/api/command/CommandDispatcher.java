package me.morpheus.metropolis.api.command;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.dispatcher.Dispatcher;

import java.util.Optional;

public interface CommandDispatcher extends Dispatcher {

    Optional<CommandMapping> register(CommandCallable callable, String alias);

    Optional<CommandMapping> register(CommandCallable callable, String... aliases);

    boolean remove(CommandMapping mapping);

    Optional<CommandMapping> remove(String alias);

    void registerDefaults();

}
