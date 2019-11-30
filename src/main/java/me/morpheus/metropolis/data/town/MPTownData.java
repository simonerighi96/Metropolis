package me.morpheus.metropolis.data.town;

import me.morpheus.metropolis.api.data.town.ImmutableTownData;
import me.morpheus.metropolis.api.data.town.TownData;
import me.morpheus.metropolis.api.data.town.TownKeys;
import me.morpheus.metropolis.data.DataVersions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.OptionalValue;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.Optional;

public class MPTownData extends AbstractData<TownData, ImmutableTownData> implements TownData {

    @Nullable private Text description;
    @Nullable private Text motd;

    MPTownData() {
        this(null, null);
    }

    MPTownData(@Nullable Text description, @Nullable Text motd) {
        this.description = description;
        this.motd = motd;
        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        registerKeyValue(TownKeys.DESCRIPTION, this::description);
        registerKeyValue(TownKeys.MOTD, this::motd);

        registerFieldGetter(TownKeys.DESCRIPTION, this::getDescription);
        registerFieldGetter(TownKeys.MOTD, this::getMotd);

        // Only on mutable implementation
        registerFieldSetter(TownKeys.DESCRIPTION, this::setDescription);
        registerFieldSetter(TownKeys.MOTD, this::setMotd);
    }

    @Override
    public Optional<TownData> fill(DataHolder dataHolder, MergeFunction overlap) {
        TownData merged = overlap.merge(this, dataHolder.get(TownData.class).orElse(null));
        this.description = merged.description().get().orElse(null);
        this.motd = merged.motd().get().orElse(null);

        return Optional.of(this);
    }

    @Override
    public Optional<TownData> from(DataContainer container) {
        Optional<Text> description = container.getSerializable(TownKeys.DESCRIPTION.getQuery(), Text.class);
        this.description = description.orElse(null);

        Optional<Text> motd = container.getSerializable(TownKeys.MOTD.getQuery(), Text.class);
        this.motd = motd.orElse(null);

        return Optional.of(this);
    }

    @Override
    public TownData copy() {
        return new MPTownData(this.description, this.motd);
    }

    @Override
    public ImmutableTownData asImmutable() {
        return new ImmutableMPTownData(this.description, this.motd);
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
    public OptionalValue<Text> description() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(TownKeys.DESCRIPTION, this.description);
    }

    @Override
    public OptionalValue<Text> motd() {
        return Sponge.getRegistry().getValueFactory().createOptionalValue(TownKeys.MOTD, this.motd);
    }

    @Nullable
    private Text getDescription() {
        return this.description;
    }

    private void setDescription(Optional<Text> description) {
        this.description = description.orElse(null);
    }

    @Nullable
    private Text getMotd() {
        return this.motd;
    }

    private void setMotd(Optional<Text> motd) {
        this.motd = motd.orElse(null);
    }
}
