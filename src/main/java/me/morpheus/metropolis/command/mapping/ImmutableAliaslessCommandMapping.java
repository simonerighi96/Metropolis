package me.morpheus.metropolis.command.mapping;

import com.google.common.base.MoreObjects;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandMapping;

import java.util.Collections;
import java.util.Set;

public class ImmutableAliaslessCommandMapping implements CommandMapping {

    private final String primary;
    private final CommandCallable callable;

    public ImmutableAliaslessCommandMapping(CommandCallable callable, String primary) {
        this.primary = primary;
        this.callable = callable;
    }

    @Override
    public String getPrimaryAlias() {
        return this.primary;
    }

    @Override
    public Set<String> getAllAliases() {
        return Collections.singleton(this.primary);
    }

    @Override
    public CommandCallable getCallable() {
        return this.callable;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("primary", this.primary)
                .add("spec", this.callable)
                .toString();
    }
}
