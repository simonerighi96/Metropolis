package me.morpheus.metropolis.api.command;

import me.morpheus.metropolis.api.command.args.MPGenericArguments;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.parsing.InputTokenizer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractMPCommand implements CommandCallable {

    private final CommandElement args;
    private final InputTokenizer tokenizer;

    protected AbstractMPCommand(CommandElement args, InputTokenizer tokenizer) {
        this.args = args;
        this.tokenizer = tokenizer;
    }

    protected AbstractMPCommand() {
        this(MPGenericArguments.empty(), InputTokenizer.rawInput());
    }

    @Override
    public final CommandResult process(CommandSource source, String arguments) throws CommandException {
        final CommandArgs commandArgs = new CommandArgs(arguments, this.tokenizer.tokenize(arguments, false));
        final CommandContext context = new CommandContext();

        this.args.parse(source, commandArgs, context);

        return process(source, context);
    }

    protected abstract CommandResult process(CommandSource source, CommandContext context) throws CommandException;

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition) throws CommandException {
        final CommandArgs args = new CommandArgs(arguments, this.tokenizer.tokenize(arguments, true));
        final CommandContext ctx = new CommandContext();
        if (targetPosition != null) {
            ctx.putArg(CommandContext.TARGET_BLOCK_ARG, targetPosition);
        }
        ctx.putArg(CommandContext.TAB_COMPLETION, true);
        final List<String> ret = this.args.complete(source, args, ctx);
        return Collections.unmodifiableList(ret);
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        final Text.Builder builder = Text.builder();
        getShortDescription(source).ifPresent((a) -> builder.append(a, Text.NEW_LINE));
        builder.append(getUsage(source));
        return Optional.of(builder.build());
    }

    @Override
    public Text getUsage(CommandSource source) {
        return this.args.getUsage(source);
    }
}
