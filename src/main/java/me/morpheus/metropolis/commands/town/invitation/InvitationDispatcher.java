package me.morpheus.metropolis.commands.town.invitation;

import me.morpheus.metropolis.command.AbstractCommandDispatcher;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public final class InvitationDispatcher extends AbstractCommandDispatcher {

    @Override
    public boolean testPermission(CommandSource source) {
        return true;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of("Invitation main command"));
    }

    @Override
    public void registerDefaults() {
        register(new ListCommand(), "list");
    }
}