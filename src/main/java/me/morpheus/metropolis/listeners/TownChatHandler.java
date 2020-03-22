package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.api.town.TownService;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

import java.util.Optional;

public final class TownChatHandler {

    @Listener(order = Order.FIRST, beforeModifications = true)
    public void onChat(MessageChannelEvent.Chat event, @Root Player player) {
        final Optional<CitizenData> cdOpt = player.get(CitizenData.class);

        if (!cdOpt.isPresent()) {
            return;
        }

        final TownService ts = Sponge.getServiceManager().provideUnchecked(TownService.class);
        final Optional<Town> tOpt = ts.get(cdOpt.get().town().get().intValue());

        if (!tOpt.isPresent()) {
            return;
        }

        if (cdOpt.get().chat().get().booleanValue()) {
            event.setChannel(tOpt.get().getMessageChannel());
        }
    }
}
