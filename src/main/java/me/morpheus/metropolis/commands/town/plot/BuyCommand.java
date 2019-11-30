package me.morpheus.metropolis.commands.town.plot;

import me.morpheus.metropolis.api.data.plot.PlotData;
import me.morpheus.metropolis.api.data.plot.PlotKeys;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.command.AbstractHomeTownCommand;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

class BuyCommand extends AbstractHomeTownCommand {

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t, PlotData pd) throws CommandException {
        if (!pd.forSale().get().booleanValue()) {
            source.sendMessage(TextUtil.watermark(TextColors.RED, "This plot is not for sale"));
            return CommandResult.empty();
        }

        pd.set(PlotKeys.OWNER, Optional.of(source.getUniqueId()));
        source.sendMessage(TextUtil.watermark(TextColors.AQUA, "You bought this plot for ", pd.price().get()));
        pd.set(PlotKeys.PRICE, 0.0);

        return CommandResult.success();
    }

    @Override
    public boolean testPermission(Player source, CitizenData cd, PlotData pd) {
        return true;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.empty();
    }
}
