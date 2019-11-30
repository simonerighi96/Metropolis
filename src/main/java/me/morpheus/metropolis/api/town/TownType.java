package me.morpheus.metropolis.api.town;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(TownTypes.class)
public interface TownType extends CatalogType {

    String getTaxFunction();

    double getSpawnCost();

    double getPlotCost();

    double getOutpostCost();

    int getMaxCitizen();

    int getMaxPlot();

}
