package me.morpheus.metropolis.api.data.town;

import me.morpheus.metropolis.api.util.MPTypeTokens;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.TypeTokens;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public final class TownKeys {

    public static final Key<OptionalValue<Text>> DESCRIPTION = Key.builder()
            .id("description")
            .name("Description")
            .query(DataQuery.of("Description"))
            .type(TypeTokens.OPTIONAL_TEXT_VALUE_TOKEN)
            .build();

    public static final Key<OptionalValue<Text>> MOTD = Key.builder()
            .id("motd")
            .name("Motd")
            .query(DataQuery.of("Motd"))
            .type(TypeTokens.OPTIONAL_TEXT_VALUE_TOKEN)
            .build();

    public static final Key<MapValue<String, Location<World>>> OUTPOSTS = Key.builder()
            .id("outposts")
            .name("Outposts")
            .query(DataQuery.of("Outposts"))
            .type(MPTypeTokens.STRING_LOCATION_MAP_VALUE_TOKEN)
            .build();

    public static final Key<Value<Double>> TAX = Key.builder()
            .id("tax")
            .name("Tax")
            .query(DataQuery.of("Tax"))
            .type(TypeTokens.DOUBLE_VALUE_TOKEN)
            .build();

    private TownKeys() {}
}
