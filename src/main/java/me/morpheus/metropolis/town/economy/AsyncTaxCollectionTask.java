package me.morpheus.metropolis.town.economy;

import me.morpheus.metropolis.Metropolis;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.user.UserStorageService;

public class AsyncTaxCollectionTask {

    public static void run(Task task) {
        final UserStorageService uss = Sponge.getServiceManager().provideUnchecked(UserStorageService.class);
        final PluginContainer plugin = Sponge.getPluginManager().getPlugin(Metropolis.ID).get();

        Sponge.getScheduler().createTaskBuilder()
                .name(Metropolis.ID + "+tax")
                .delayTicks(2L)
                .execute(new TaxCollectionTask(uss.getAll().iterator()))
                .submit(plugin);
    }
}
