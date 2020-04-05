package me.morpheus.metropolis.commands.town;

import me.morpheus.metropolis.Metropolis;
import me.morpheus.metropolis.api.command.AbstractCitizenCommand;
import me.morpheus.metropolis.api.command.args.parsing.MinimalInputTokenizer;
import me.morpheus.metropolis.api.data.citizen.CitizenData;
import me.morpheus.metropolis.api.data.town.outpost.OutpostData;
import me.morpheus.metropolis.api.town.Town;
import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.args.parsing.InputTokenizer;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.teleport.TeleportTypes;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

class OutpostCommand extends AbstractCitizenCommand {

    OutpostCommand() {
        super(
                GenericArguments.onlyOne(GenericArguments.text(Text.of("name"), TextSerializers.FORMATTING_CODE, false)),
                MinimalInputTokenizer.INSTANCE,
                Metropolis.ID + ".commands.town.outpost",
                Text.of()
        );
    }

    @Override
    public CommandResult process(Player source, CommandContext context, CitizenData cd, Town t) throws CommandException {
        final Text name = context.requireOne("name");

        final Optional<OutpostData> odOpt = t.get(OutpostData.class);

        if (!odOpt.isPresent()) {
            source.sendMessage(TextUtil.watermark(TextColors.RED, "Your town has no outpost"));
            return CommandResult.empty();
        }

        final Location<World> out = odOpt.get().outposts().get().get(name.toPlain());

        if (out == null) {
            source.sendMessage(TextUtil.watermark(TextColors.RED, "There is no outpost named ", name));
            return CommandResult.empty();
        }
        final Optional<Entity> vehicleOpt = source.getVehicle();
        if (vehicleOpt.isPresent()) {
            boolean success = source.setVehicle(null);
            if (!success) {
                source.sendMessage(TextUtil.watermark(TextColors.RED, "Dismount from your vehicle"));
                return CommandResult.empty();
            }
        }
        try (final CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
            final PluginContainer plugin = Sponge.getPluginManager().getPlugin(Metropolis.ID).get();
            frame.addContext(EventContextKeys.TELEPORT_TYPE, TeleportTypes.COMMAND);
            frame.addContext(EventContextKeys.PLUGIN, plugin);
            source.transferToWorld(out.getExtent(), out.getPosition());
        }
        return CommandResult.success();
    }
}
