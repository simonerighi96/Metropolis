package me.morpheus.metropolis.flag;

import me.morpheus.metropolis.api.custom.CustomResourceLoaders;
import me.morpheus.metropolis.api.flag.Flag;
import me.morpheus.metropolis.api.flag.Flags;
import me.morpheus.metropolis.custom.CustomResourceLoaderRegistryModule;
import org.spongepowered.api.registry.AdditionalCatalogRegistryModule;
import org.spongepowered.api.registry.util.RegisterCatalog;
import org.spongepowered.api.registry.util.RegistrationDependency;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RegistrationDependency(CustomResourceLoaderRegistryModule.class)
public class FlagRegistryModule implements AdditionalCatalogRegistryModule<Flag> {

    @RegisterCatalog(Flags.class)
    private final Map<String, Flag> map = new HashMap<>(7);

    @Override
    public void registerDefaults() {
        final Collection<Flag> flags = CustomResourceLoaders.FLAG.load();

        for (Flag flag : flags) {
            register(flag);
        }
    }

    private void register(Flag flag) {
        this.map.put(flag.getId(), flag);
    }

    @Override
    public void registerAdditionalCatalog(Flag extraCatalog) {
        this.map.putIfAbsent(extraCatalog.getId(), extraCatalog);
    }

    @Override
    public Optional<Flag> getById(String id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public Collection<Flag> getAll() {
        return Collections.unmodifiableCollection(this.map.values());
    }
}
