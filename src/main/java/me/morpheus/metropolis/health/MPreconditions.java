package me.morpheus.metropolis.health;

import me.morpheus.metropolis.api.health.Incident;
import me.morpheus.metropolis.api.rank.Rank;
import me.morpheus.metropolis.error.MPGenericErrors;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.permission.PermissionService;

import javax.annotation.Nullable;
import java.util.Optional;

public final class MPreconditions {

    @Nullable
    public static Incident checkDefaultRanks() {
        Optional<Rank> mayorOpt = Sponge.getRegistry().getType(Rank.class, "mayor");
        Optional<Rank> citizenOpt = Sponge.getRegistry().getType(Rank.class, "citizen");

        if (!mayorOpt.isPresent() || !citizenOpt.isPresent()) {
            String mayor = mayorOpt.map(Rank::toString).orElse("NONE");
            String citizen = citizenOpt.map(Rank::toString).orElse("NONE");

            return new MPIncident(MPGenericErrors.rank(mayor, citizen));
        }

        return null;
    }

    @Nullable
    public static Incident checkPermissionService() {
        Optional<ProviderRegistration<PermissionService>> ops = Sponge.getServiceManager().getRegistration(PermissionService.class);

        if (!ops.isPresent() || ops.get().getPlugin() == Sponge.getPlatform().getContainer(Platform.Component.IMPLEMENTATION)) {
            return new MPIncident(MPGenericErrors.permissionService());
        }
        return null;
    }

    private MPreconditions() {}
}
