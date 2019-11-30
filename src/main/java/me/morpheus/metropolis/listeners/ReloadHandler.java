package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.MPLog;
import me.morpheus.metropolis.api.config.ConfigService;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;

import java.io.IOException;

public class ReloadHandler {

    @Listener
    public void onReload(GameReloadEvent event) {
        MPLog.getLogger().info("Reloading config...");
        try {
            Sponge.getServiceManager().provideUnchecked(ConfigService.class).reload();
            MPLog.getLogger().info("Done");
        } catch (ObjectMappingException | IOException e) {
            MPLog.getLogger().error("Error while reloading config", e);
        }
    }

}
