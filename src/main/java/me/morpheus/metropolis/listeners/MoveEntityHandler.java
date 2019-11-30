package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.plot.PlotService;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.api.town.TownService;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class MoveEntityHandler {

    @Listener(beforeModifications = true)
    public void onMove(MoveEntityEvent.Position event) {
        if (event.getTargetEntity().getType() != EntityTypes.PLAYER) {
            return;
        }

        final Player player = (Player) event.getTargetEntity();
        final Transform<World> from = event.getFromTransform();
        final Transform<World> to = event.getToTransform();

        if (from.getLocation().getChunkPosition().equals(to.getLocation().getChunkPosition())) {
            return;
        }

        final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
        final Optional<PlotData> pdOpt = ps.get(to.getLocation());
        if (!ps.get(from.getLocation()).isPresent() && !pdOpt.isPresent()) {
            return;
        }

        if (!pdOpt.isPresent()) {
            player.sendMessage(TextUtil.watermark(TextColors.GREEN, "~ Wilderness"));
            return;
        }

        final TownService ts = Sponge.getServiceManager().provideUnchecked(TownService.class);
        final Town t = ts.get(pdOpt.get().town().get().intValue()).get();

        final Text pvp = Text.of(TextColors.RED, "[PvP ", t.getPvP().getName(), "]");
        player.sendMessage(TextUtil.watermark(TextColors.AQUA, "~ ", t.getName(), TextColors.GRAY, " - ", pvp));
    }
}
