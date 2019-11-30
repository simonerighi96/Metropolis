package me.morpheus.metropolis.api.town;

import org.spongepowered.api.data.DataView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;
import java.util.stream.Stream;

public interface TownService {

    Optional<Town> create(Text name, Location<World> spawn);

    Optional<Town> delete(int id);

    Optional<Town> get(int id);

    boolean exist(int id);

    Stream<Town> towns();

    void save(Town town);

    void saveAll();

    void loadAll();

    Town from(DataView view);

}
