package me.morpheus.metropolis.commands.town.citizen;

import me.morpheus.metropolis.Metropolis;
import me.morpheus.metropolis.api.command.AbstractCitizenCommand;
import me.morpheus.metropolis.api.command.args.MPGenericArguments;
import me.morpheus.metropolis.api.command.args.parsing.MinimalInputTokenizer;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.data.citizen.CitizenKeys;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.util.NameUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ListCommand extends AbstractCitizenCommand {

    public ListCommand() {
        super(
                GenericArguments.optional(MPGenericArguments.town(Text.of("town"))),
                MinimalInputTokenizer.INSTANCE,
                Metropolis.ID + ".commands.town.citizen.list",
                Text.of()
        );
    }

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t) throws CommandException {
        final Town town = context.<Town>getOne(Text.of("town")).orElse(t);
        final UserStorageService uss = Sponge.getServiceManager().provideUnchecked(UserStorageService.class);
        final List<Text> citizens = town.getCitizens()
                .map(uss::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(NameUtil::getDisplayName)
                .collect(Collectors.toList());

        PaginationList.builder()
                .title(Text.of(TextColors.GOLD, "[ ", TextColors.YELLOW, town.getName(), TextColors.GOLD, " ]"))
                .contents(Text.of(TextColors.AQUA, Text.joinWith(Text.of(','), citizens)))
                .padding(Text.of(TextColors.GOLD, "-"))
                .sendTo(source);

        return CommandResult.success();
    }
}
