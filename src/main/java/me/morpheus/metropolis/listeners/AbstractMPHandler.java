package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.config.ConfigService;
import me.morpheus.metropolis.api.config.GlobalConfig;
import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.flag.Flag;
import me.morpheus.metropolis.api.plot.PlotService;
import me.morpheus.metropolis.api.rank.Rank;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AbstractMPHandler {

    protected boolean hasPermission(Object root, List<Transaction<BlockSnapshot>> transactions, Flag flag) {
        final boolean isPlayer = root instanceof Player;
        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);

        for (Transaction<BlockSnapshot> transaction : transactions) {
            final Optional<PlotData> pdOpt = ps.get(transaction.getOriginal().getLocation().get());

            if (pdOpt.isPresent()) {
                if (!isPlayer) {
                    return false;
                }

                if (!hasPermission((Player) root, pdOpt.get(), flag)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean hasPermission(@Nullable Object root, Collection<Location<World>> locations, Flag flag) {
        final boolean isPlayer = root instanceof Player;
        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);

        for (Location<World> loc : locations) {
            final Optional<PlotData> pdOpt = ps.get(loc);

            if (pdOpt.isPresent()) {
                if (!isPlayer) {
                    return false;
                }

                if (!hasPermission((Player) root, pdOpt.get(), flag)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected boolean hasPermission(User source, PlotData pd, Flag flag) {
        final Optional<CitizenData> cdOpt = source.get(CitizenData.class);
        return cdOpt.isPresent() && hasPermission(source.getUniqueId(), cdOpt.get(), pd, flag);
    }

    protected boolean hasPermission(UUID source, CitizenData cd, PlotData pd, Flag flag) {
        if (pd.town().get().intValue() != cd.town().get().intValue()) {
            return false;
        }

        final Rank rank = cd.rank().get();

        final int value = pd.getPermission(flag);
        if (value != Integer.MIN_VALUE) {
            return rank.getPermission(flag) > value;
        }

        final GlobalConfig config = Sponge.getServiceManager().provideUnchecked(ConfigService.class).getGlobal();
        final int perm = rank.getPermission(flag);

        if (!pd.owner().get().isPresent()) {
            return perm > config.getTownCategory().getPlotCategory().getUnownedPermission(flag);
        }

        final UUID ownerId = pd.owner().get().get();
        if (ownerId.equals(source)) {
            return true;
        }

        final User owner = Sponge.getServiceManager().provideUnchecked(UserStorageService.class).get(ownerId).get();

        final Optional<CitizenData> optOwnerData = owner.get(CitizenData.class);
        if (!optOwnerData.isPresent()) {
            return perm > config.getTownCategory().getPlotCategory().getUnownedPermission(flag);
        }

        if (optOwnerData.get().friends().contains(source)) {
            return true;
        }

        return perm > optOwnerData.get().rank().get().getPermission(flag);
    }

    @Nullable
    protected User getSource(Event event) {
        final Optional<User> ownerOpt = event.getContext().get(EventContextKeys.OWNER);
        if (ownerOpt.isPresent()) {
            return ownerOpt.get();
        }

        final Optional<User> notifierOpt = event.getContext().get(EventContextKeys.NOTIFIER);
        if (notifierOpt.isPresent()) {
            return notifierOpt.get();
        }

        return null;
    }


}
