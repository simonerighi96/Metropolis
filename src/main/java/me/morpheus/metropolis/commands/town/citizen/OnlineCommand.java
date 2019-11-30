package me.morpheus.metropolis.commands.town.citizen;

import me.morpheus.metropolis.api.command.AbstractCitizenCommand;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.data.citizen.CitizenKeys;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.util.NameUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class OnlineCommand extends AbstractCitizenCommand {

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t) throws CommandException {
        final List<Text> citizens = Sponge.getServer().getOnlinePlayers().stream()
                .filter(p -> p.get(CitizenKeys.TOWN).filter(i -> i.intValue() == t.getId()).isPresent())
                .filter(p -> !p.get(Keys.VANISH).orElse(false))
                .map(NameUtil::getDisplayName)
                .collect(Collectors.toList());

        PaginationList.builder()
                .title(Text.of(TextColors.GOLD, "[", TextColors.YELLOW, "Online citizens", TextColors.GOLD, "]"))
                .contents(Text.of(TextColors.AQUA, Text.joinWith(Text.NEW_LINE, citizens)))
                .padding(Text.of(TextColors.GOLD, "-"))
                .sendTo(source);

        return CommandResult.success();
    }

    @Override
    public boolean testPermission(Player source, CitizenData cd) {
        return true;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.of(Text.of("List the outposts of your town"));
    }
}
