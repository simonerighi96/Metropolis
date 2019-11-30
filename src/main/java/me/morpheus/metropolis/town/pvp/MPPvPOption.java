package me.morpheus.metropolis.town.pvp;

import com.google.common.base.MoreObjects;
import me.morpheus.metropolis.api.town.pvp.PvPOption;
import org.spongepowered.api.entity.living.player.Player;

import java.util.function.BiPredicate;

class MPPvPOption implements PvPOption {

    private final String id;
    private final String name;
    private final BiPredicate<Player, Player> predicate;

    MPPvPOption(String id, String name, BiPredicate<Player, Player> predicate) {
        this.id = id;
        this.name = name;
        this.predicate = predicate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean canDamage(Player source, Player target) {
        return this.predicate.test(source, target);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Id", this.id)
                .add("Name", this.name)
                .toString();
    }
}
