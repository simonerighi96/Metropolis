package me.morpheus.metropolis.commands.town.plot;

import me.morpheus.metropolis.Metropolis;
import me.morpheus.metropolis.api.command.AbstractHomeTownCommand;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.data.plot.PlotKeys;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

class DisownCommand extends AbstractHomeTownCommand {

    public DisownCommand() {
        super(
                Metropolis.ID + ".commands.town.plot.disown",
                Text.of()
        );
    }

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t, PlotData pd) throws CommandException {
        pd.set(PlotKeys.OWNER, Optional.empty());
        source.sendMessage(TextUtil.watermark(TextColors.AQUA, "The plot owner has been removed"));

        return CommandResult.success();
    }
}

