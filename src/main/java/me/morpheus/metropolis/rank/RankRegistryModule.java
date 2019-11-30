package me.morpheus.metropolis.rank;

import me.morpheus.metropolis.api.custom.CustomResourceLoaders;
import me.morpheus.metropolis.api.rank.Rank;
import me.morpheus.metropolis.api.rank.Ranks;
import me.morpheus.metropolis.custom.CustomResourceLoaderRegistryModule;
import me.morpheus.metropolis.flag.FlagRegistryModule;
import org.spongepowered.api.registry.AdditionalCatalogRegistryModule;
import org.spongepowered.api.registry.util.RegisterCatalog;
import org.spongepowered.api.registry.util.RegistrationDependency;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RegistrationDependency({FlagRegistryModule.class, CustomResourceLoaderRegistryModule.class})
public final class RankRegistryModule implements AdditionalCatalogRegistryModule<Rank> {

    @RegisterCatalog(Ranks.class)
    private final Map<String, Rank> map = new HashMap<>(2);

    @Override
    public void registerDefaults() {
        final Collection<Rank> ranks = CustomResourceLoaders.RANK.load();

        for (Rank rank : ranks) {
            register(rank);
        }
    }

    private void register(Rank rank) {
        this.map.put(rank.getId(), rank);
    }

    @Override
    public void registerAdditionalCatalog(Rank extraCatalog) {
        this.map.putIfAbsent(extraCatalog.getId(), extraCatalog);
    }

    @Override
    public Optional<Rank> getById(String id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public Collection<Rank> getAll() {
        return Collections.unmodifiableCollection(this.map.values());
    }
}
