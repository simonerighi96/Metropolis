package me.morpheus.metropolis.commands.town;

import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.data.town.TownData;
import me.morpheus.metropolis.api.town.Town;

import me.morpheus.metropolis.api.command.AbstractMPCommand;
import me.morpheus.metropolis.api.command.args.MPGenericArguments;
import me.morpheus.metropolis.api.town.visibility.Visibilities;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.parsing.InputTokenizer;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

class InfoCommand extends AbstractMPCommand {

    InfoCommand() {
        super(MPGenericArguments.townOrHomeTown(Text.of("town")), InputTokenizer.rawInput());
    }

    @Override
    public CommandResult process(CommandSource source, CommandContext context) throws CommandException {
        final Optional<Town> tOpt = context.getOne("town");

        if (!tOpt.isPresent()) {
            source.sendMessage(TextUtil.watermark(TextColors.RED, "You don't have a town"));
            return CommandResult.empty();
        }

        final Town t = tOpt.get();
        final Optional<TownData> tdOpt = t.get(TownData.class);
        final Text description = tdOpt.isPresent() && tdOpt.get().description().get().isPresent()
                ? Text.of(TextColors.DARK_GREEN, "Description: ", tdOpt.get().description().get().get()) :
                Text.of();

        final Text motd = tdOpt.isPresent() && tdOpt.get().motd().get().isPresent()
                ? Text.of(TextColors.DARK_GREEN, "Motd: ", tdOpt.get().motd().get().get())
                : Text.of();

        final Text spawn = canSeeSpawn(source, t)
                ? Text.of(TextColors.DARK_GREEN, "Spawn: ", TextColors.GREEN, t.getSpawn().getBlockPosition(), " in ", t.getSpawn().getExtent().getName())
                : Text.of();

        PaginationList.builder()
                .title(Text.of(TextColors.GOLD, "[", TextColors.YELLOW, t.getName(), TextColors.GOLD, "]"))
                .contents(
                        description,
                        Text.of(TextColors.DARK_GREEN, "Founded: ", TextColors.GREEN, t.getFounded().truncatedTo(ChronoUnit.SECONDS)),
                        Text.of(TextColors.DARK_GREEN, "Type: ", TextColors.GREEN, t.getType().getName()),
                        spawn,
                        Text.of(TextColors.DARK_GREEN, "Tag: ", TextColors.GREEN, t.getTag()),
                        Text.of(TextColors.DARK_GREEN, "PvP: ", TextColors.GREEN, t.getPvP().getName()),
                        Text.of(TextColors.DARK_GREEN, "Visibility: ", TextColors.GREEN, t.getVisibility().getName()),
                        motd
                )
                .padding(Text.of(TextColors.GOLD, "-"))
                .sendTo(source);

        return CommandResult.success();
    }

    private boolean canSeeSpawn(CommandSource source, Town t) {
        if (t.getVisibility() == Visibilities.PUBLIC) {
            return true;
        }

        if (source instanceof Player) {
            final Optional<CitizenData> cdOpt = ((Player) source).get(CitizenData.class);
            return cdOpt.isPresent() && cdOpt.get().town().get().intValue() == t.getId();
        }

        if (source instanceof ConsoleSource) {
            return true;
        }

        return false;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return true;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of("Shows a town’s town screen"));
    }
}
