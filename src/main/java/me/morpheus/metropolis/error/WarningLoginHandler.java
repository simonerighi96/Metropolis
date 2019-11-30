package me.morpheus.metropolis.error;

import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.format.TextColors;

public class WarningLoginHandler {

    @Listener
    public void onLogin(ClientConnectionEvent.Join event) {
        Player player = event.getTargetEntity();

        player.sendMessage(TextUtil.watermark(TextColors.RED, "The plugin failed to start, please check the logs"));
    }


}
