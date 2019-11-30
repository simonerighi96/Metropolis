package me.morpheus.metropolis.town.chat;

import me.morpheus.metropolis.api.data.citizen.CitizenKeys;
import me.morpheus.metropolis.api.town.Town;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.chat.ChatType;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class TownMessageChannel implements MessageChannel {

    private final int id;

    public TownMessageChannel(Town t) {
        this.id = t.getId();
    }

    @Override
    public Collection<MessageReceiver> getMembers() {
        return Sponge.getServer().getOnlinePlayers().stream()
                        .filter(p -> p.get(CitizenKeys.TOWN).map(i -> i.intValue() == this.id).orElse(false))
                        .collect(Collectors.toSet());
    }

    @Override
    public Optional<Text> transformMessage(@Nullable Object sender, MessageReceiver recipient, Text original, ChatType type) {
        return Optional.of(Text.of(TextColors.AQUA, "[TC] ", original));
    }
}
