package me.morpheus.metropolis.util;

import me.morpheus.metropolis.Metropolis;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public final class TextUtil {

    public static Text watermark(Object... objects) {
        Text plugin = Text.of(TextColors.GOLD, "[", Metropolis.NAME, "] ");
        return plugin.concat(Text.of(objects));
    }

    public static Text reset(Object... objects) {
        return Text.of(TextColors.RESET, TextStyles.RESET, Text.of(objects));
    }

    private TextUtil() {}
}
