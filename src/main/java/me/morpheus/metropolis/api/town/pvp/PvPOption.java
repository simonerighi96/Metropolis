package me.morpheus.metropolis.api.town.pvp;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(PvPOptions.class)
public interface PvPOption extends CatalogType {

    boolean canDamage(Player source, Player target);

}
