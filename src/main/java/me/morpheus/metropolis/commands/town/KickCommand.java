package me.morpheus.metropolis.commands.town;

import me.morpheus.metropolis.Metropolis;
import me.morpheus.metropolis.api.command.args.MPGenericArguments;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.api.command.AbstractCitizenCommand;
import me.morpheus.metropolis.util.NameUtil;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.parsing.InputTokenizer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Collection;
import java.util.Optional;

class KickCommand extends AbstractCitizenCommand {

    KickCommand() {
        super(
                MPGenericArguments.citizen(Text.of("citizens")),
                InputTokenizer.spaceSplitString(),
                Metropolis.ID + ".commands.town.kick",
                Text.of()
        );
    }

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t) throws CommandException {
        final Collection<User> citizens = context.getAll("citizens");

        final Text sourceName = NameUtil.getDisplayName(source);
        for (User citizen : citizens) {
            final CitizenData targetCd = citizen.get(CitizenData.class).get();

            boolean success = targetCd.rank().get().canLeave() && t.kick(citizen.getUniqueId());

            final Text citizenName = NameUtil.getDisplayName(citizen);
            if (!success) {
                source.sendMessage(TextUtil.watermark(TextColors.RED, "Unable to kick ", citizenName));
            } else {
                t.sendMessage(Text.of(citizenName, " was kicked from the town by ", sourceName));
                if (citizen.isOnline()) {
                    citizen.getPlayer().get().sendMessage(TextUtil.watermark(TextColors.AQUA, "You were kicked from the town by ", sourceName));
                }
            }
        }

        return CommandResult.success();
    }
}
