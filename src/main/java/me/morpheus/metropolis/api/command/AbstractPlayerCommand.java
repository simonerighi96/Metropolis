package me.morpheus.metropolis.api.command;

import me.morpheus.metropolis.util.TextUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.parsing.InputTokenizer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.format.TextColors;

public abstract class AbstractPlayerCommand extends AbstractMPCommand {

    protected AbstractPlayerCommand(CommandElement args, InputTokenizer tokenizer) {
        super(args, tokenizer);
    }

    protected AbstractPlayerCommand() {
        super();
    }

    @Override
    public final CommandResult process(CommandSource source, CommandContext context) throws CommandException {
        if (!(source instanceof Player)) {
            source.sendMessage(TextUtil.watermark(TextColors.RED, "You are not a player"));
            return CommandResult.empty();
        }
        final Player player = (Player) source;
        return process(player, context);
    }

    protected abstract CommandResult process(Player source, CommandContext context) throws CommandException;

    @Override
    public final boolean testPermission(CommandSource source) {
        return source instanceof Player && testPermission((Player) source);
    }

    protected abstract boolean testPermission(Player player);
}
