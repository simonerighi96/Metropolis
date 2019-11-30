package me.morpheus.metropolis.town.type;

import com.google.common.base.MoreObjects;
import me.morpheus.metropolis.api.town.TownType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
class MPTownType implements TownType {

    @Setting private String id;
    @Setting private String name;
    @Setting(value = "tax-function") private String taxFunction;
    @Setting(value = "spawn-cost") private double spawnCost;
    @Setting(value = "plot-cost") private double plotCost;
    @Setting(value = "outpost-cost") private double outpostCost;
    @Setting(value = "max-citizen") private int maxCitizen;
    @Setting(value = "max-plot") private int maxPlot;

    MPTownType(String id, String name, String taxFunction,
               double spawnCost, double plotCost, double outpostCost, int maxCitizen, int maxPlot) {
        this.id = id;
        this.name = name;
        this.taxFunction = taxFunction;
        this.spawnCost = spawnCost;
        this.plotCost = plotCost;
        this.outpostCost = outpostCost;
        this.maxCitizen = maxCitizen;
        this.maxPlot = maxPlot;
    }

    private MPTownType() {
        this.id = "dummy";
        this.name = "DUMMY";
        this.taxFunction = "0";
        this.spawnCost = 0;
        this.plotCost = 0;
        this.outpostCost = 0;
        this.maxCitizen = 0;
        this.maxPlot = 0;
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
    public String getTaxFunction() {
        return this.taxFunction;
    }

    @Override
    public double getSpawnCost() {
        return this.spawnCost;
    }

    @Override
    public double getPlotCost() {
        return this.plotCost;
    }

    @Override
    public double getOutpostCost() {
        return this.outpostCost;
    }

    @Override
    public int getMaxCitizen() {
        return this.maxCitizen;
    }

    @Override
    public int getMaxPlot() {
        return this.maxPlot;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Id", this.id)
                .add("Name", this.name)
                .toString();
    }
}
