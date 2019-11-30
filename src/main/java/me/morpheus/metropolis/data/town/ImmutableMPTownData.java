package me.morpheus.metropolis.data.town;

import me.morpheus.metropolis.api.data.town.ImmutableTownData;
import me.morpheus.metropolis.api.data.town.TownData;
import me.morpheus.metropolis.api.data.town.TownKeys;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableOptionalValue;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;

public class ImmutableMPTownData extends AbstractImmutableData<ImmutableTownData, TownData> implements ImmutableTownData {

    @Nullable private final Text description;
    @Nullable private final Text motd;

    ImmutableMPTownData(@Nullable Text description, @Nullable Text motd) {
        this.description = description;
        this.motd = motd;
        registerGetters();
    }

    @Override
    protected void registerGetters() {
        registerKeyValue(TownKeys.DESCRIPTION, this::description);
        registerKeyValue(TownKeys.MOTD, this::motd);

        registerFieldGetter(TownKeys.DESCRIPTION, this::getDescription);
        registerFieldGetter(TownKeys.MOTD, this::getMotd);
    }

    @Override
    public TownData asMutable() {
        return new MPTownData(this.description, this.motd);
    }

    @Override
    public int getContentVersion() {
        return DataVersions.TownData.CURRENT_VERSION;
    }

    @Override
    public DataContainer toContainer() {
        DataContainer container = super.toContainer();
        if (this.description != null) {
            container.set(TownKeys.DESCRIPTION.getQuery(), this.description);
        }
        if (this.motd != null) {
            container.set(TownKeys.MOTD.getQuery(), this.motd);
        }
        return container;
    }

    @Override
    public ImmutableOptionalValue<Text> description() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(TownKeys.DESCRIPTION, this.description).asImmutable();
    }

    @Override
    public ImmutableOptionalValue<Text> motd() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(TownKeys.MOTD, this.motd).asImmutable();
    }

    @Nullable
    private Text getDescription() {
        return this.description;
    }

    @Nullable
    private Text getMotd() {
        return this.motd;
    }
}
