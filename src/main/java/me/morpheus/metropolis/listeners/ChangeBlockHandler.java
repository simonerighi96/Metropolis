package me.morpheus.metropolis.listeners;

import me.morpheus.metropolis.api.flag.Flags;
import me.morpheus.metropolis.api.plot.PlotService;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.entity.FallingBlock;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;

public class ChangeBlockHandler extends AbstractMPHandler {

    @Listener(beforeModifications = true)
    public void onBlockPre(ChangeBlockEvent.Pre event) {
        final Object root = event.getCause().root();

        if (event.getCause().getContext().asMap().isEmpty() && event.getCause().all().size() == 1
                && event.getCause().root() instanceof LocatableBlock) { //TODO Sponge ? Wtf ?
            final PlotService ps = Sponge.getServiceManager().provideUnchecked(PlotService.class);
            final List<Location<World>> locations = event.getLocations();
            final long count = locations.stream().filter(l -> ps.get(((LocatableBlock) root).getLocation()).isPresent()).count();
            if (count != 0L && count != locations.size()) {
                event.setCancelled(true);
            }
            return;
        }

        final User source = getSource(event);
        final Object cause = source == null ? root : source;

        if (!hasPermission(cause, event.getLocations(), Flags.BLOCK_CHANGE)) {
            event.setCancelled(true);
            if (source != null) {
                source.getPlayer().ifPresent(this::sendMessage);
            }
        }
    }

    @Listener(beforeModifications = true)
    public void onBlockPlace(ChangeBlockEvent.Place event) {
        final Object root = event.getCause().root();

        if (root instanceof TileEntity && ((TileEntity) root).getType() == TileEntityTypes.PISTON) {
            return;
        }

        if (event.getCause().getContext().asMap().isEmpty() && event.getCause().all().size() == 1
                && root instanceof FallingBlock) {
            //TODO Sponge ? Wtf ?
            return;
        }

        if (root == Sponge.getGame() && event.getCause().all().size() == 1
                && !event.getContext().asMap().containsKey(EventContextKeys.OWNER)
                && !event.getContext().asMap().containsKey(EventContextKeys.NOTIFIER)) {
            //TODO Sponge ? Wtf ?
            return;
        }

        final User source = getSource(event);
        final Object cause = source == null ? root : source;

        if (!hasPermission(cause, event.getTransactions(), Flags.BLOCK_PLACE)) {
            event.filterAll();
            event.setCancelled(true);
            if (source != null) {
                source.getPlayer().ifPresent(this::sendMessage);
            }
        }
    }

    @Listener(beforeModifications = true)
    public void onBlockBreak(ChangeBlockEvent.Break event) {
        final Object root = event.getCause().root();

        if (root instanceof TileEntity && ((TileEntity) root).getType() == TileEntityTypes.PISTON) {
            return;
        }

        if (root == Sponge.getGame() && event.getCause().all().size() == 1
                && !event.getContext().asMap().containsKey(EventContextKeys.OWNER)
                && !event.getContext().asMap().containsKey(EventContextKeys.NOTIFIER)) {
            //TODO Sponge ? Wtf ?
            return;
        }

        if (event.getCause().getContext().asMap().isEmpty() && event.getCause().all().size() == 1
                && root instanceof FallingBlock) { //TODO Sponge ? Wtf ?
            return;
        }

        final User source = getSource(event);
        final Object cause = source == null ? root : source;

        if (!hasPermission(cause, event.getTransactions(), Flags.BLOCK_BREAK)) {
            event.filterAll();
            event.setCancelled(true);
            if (source != null) {
                source.getPlayer().ifPresent(this::sendMessage);
            }
        }
    }

    private void sendMessage(Player player) {
        player.sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "You don't have permission to do this"));
    }
}
