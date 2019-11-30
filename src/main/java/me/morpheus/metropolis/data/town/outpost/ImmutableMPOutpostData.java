package me.morpheus.metropolis.data.town.outpost;

import me.morpheus.metropolis.api.data.town.TownKeys;
import me.morpheus.metropolis.api.data.town.outpost.ImmutableOutpostData;
import me.morpheus.metropolis.api.data.town.outpost.OutpostData;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableMapValue;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;

public class ImmutableMPOutpostData extends AbstractImmutableData<ImmutableOutpostData, OutpostData> implements ImmutableOutpostData {

    @Nullable private final Map<String, Location<World>> outposts;

    ImmutableMPOutpostData(@Nullable Map<String, Location<World>> outposts) {
        this.outposts = outposts;
        registerGetters();
    }
    @Override
    protected void registerGetters() {
        registerKeyValue(TownKeys.OUTPOSTS, this::outposts);

        registerFieldGetter(TownKeys.OUTPOSTS, this::getOutposts);
    }

    @Override
    public OutpostData asMutable() {
        return new MPOutpostData(this.outposts);
    }

    @Override
    public int getContentVersion() {
        return DataVersions.OutpostData.CURRENT_VERSION;
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        if (this.outposts != null && !this.outposts.isEmpty()) {
            container.set(TownKeys.OUTPOSTS.getQuery(), this.outposts);
        }

        return container;
    }

    @Override
    public ImmutableMapValue<String, Location<World>> outposts() {
        if (this.outposts == null) {
            return Sponge.getRegistry().getValueFactory().createMapValue(TownKeys.OUTPOSTS, Collections.emptyMap()).asImmutable();
        }
        return Sponge.getRegistry().getValueFactory().createMapValue(TownKeys.OUTPOSTS, this.outposts).asImmutable();
    }

    @Nullable
    private Map<String, Location<World>> getOutposts() {
        return this.outposts;
    }
}
